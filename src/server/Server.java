package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static Dictionary dictionary;

	public static void main(String[] args) {


		dictionary = new Dictionary();

		try {
			// ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
			ServerSocket ss = new ServerSocket(1234);
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
