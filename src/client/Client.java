package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		UI ui = new UI();
		ui.start();
		try {
			// Socket s = new Socket(args[0], Integer.parseInt(args[1]));
			Socket s = new Socket("localhost", 1234);
			DataInputStream reader = new DataInputStream(s.getInputStream());
			DataOutputStream writer = new DataOutputStream(s.getOutputStream());



			

		} catch (UnknownHostException e) {
			System.out.println("Error: UNKNOWN HOST");
		} catch (IOException e) {
			System.out.println("Error: IO EXCEPTION");
		}
		
		
		
	}
}
