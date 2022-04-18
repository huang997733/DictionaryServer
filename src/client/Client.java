/**
 * author: Ziyang Huang 1067800
 */
package client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private String address;
	private static int port;
	private UI ui;
	private Socket s;
	private DataOutputStream writer;
	private DataInputStream reader;

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Please enter: <address> <port>");
			return;
		}

		try {
			port = Integer.parseInt(args[1]);
			if (port < 1024 || port > 49151) {
				System.out.println("Invalid port: please use port between 1024 and 49151");
				return;
			}
		} catch (Exception e) {
			System.out.println("Port format incorrect");
			return;
		}


		Client client = new Client(args[0], port);
		client.startUI();

	}

	public Client(String address, int port) {
		this.address = address;
		this.port = port;
		connect();
	}

	public void connect(){
		try {
			s = new Socket(this.address, this.port);
			reader = new DataInputStream(s.getInputStream());
			writer = new DataOutputStream(s.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("Error: UNKNOWN HOST");
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Error: IO EXCEPTION");
			System.exit(-1);
		}
	}

	public void startUI() {
		this.ui = new UI(this);
		ui.start();
	}

	public JSONObject send(JSONObject msg) {
		JSONObject reply = new JSONObject();
		try {
			writer.writeUTF(msg.toJSONString());
			writer.flush();

			JSONParser parser = new JSONParser();
			try {
				reply = (JSONObject) parser.parse(reader.readUTF());
			} catch (ParseException e) {
				reply.put("error", "Parse Exception");
			}

		} catch (IOException e) {
			reply.put("error", "IO Exception");
		}


		return reply;
	}
}
