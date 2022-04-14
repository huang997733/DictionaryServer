package client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private String address;
	private int port;
	private UI ui;
	private Socket s;
	private DataOutputStream writer;
	private DataInputStream reader;

	public static void main(String[] args) {
		Client client = new Client("localhost", 1234);
		client.startUI();

	}

	public Client(String address, int port) {
		this.address = address;
		this.port = port;
		connect();
	}

	public void connect(){
		try {
			// Socket s = new Socket(args[0], Integer.parseInt(args[1]));
			s = new Socket("localhost", 1234);
			reader = new DataInputStream(s.getInputStream());
			writer = new DataOutputStream(s.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("Error: UNKNOWN HOST");
		} catch (IOException e) {
			System.out.println("Error: IO EXCEPTION");
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
