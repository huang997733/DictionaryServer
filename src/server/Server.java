/**
 * author: Ziyang Huang 1067800
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int port;
	private static Dictionary dictionary;

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Please enter: <port> <dictionary>");
			return;
		}

		try {
			port = Integer.parseInt(args[0]);
			if (port < 1024 || port > 49151) {
				System.out.println("Invalid port: please use port between 1024 and 49151");
				return;
			}
		} catch (Exception e) {
			System.out.println("Port format incorrect");
			return;
		}

		dictionary = new Dictionary(args[1]);

		try {
			// ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				Socket s = ss.accept();
				ConnectionThread clientThread = new ConnectionThread(s, dictionary);
				clientThread.start();
				
			}


		} catch (NumberFormatException e) {
			System.out.println("Error: NumberFormatException");
		} catch (IOException e) {
			System.out.println("Error: IO EXCEPTION");
		}
		
	}

}
