package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private String address;
	private int port;
	private UI ui;

	public static void main(String[] args) {


		Client client = new Client(args[0], Integer.parseInt(args[1]));
		client.startUI();


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

	public Client(String address, int port) {
		this.address = address;
		this.port = port;

	}

	public void startUI() {
		this.ui = new UI(this);
		ui.start();
	}
}
