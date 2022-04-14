package server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConnectionThread extends Thread {

    private Socket clientSocket;
    private Dictionary dictionary;


    @Override
    public void run() {
        try {
            DataInputStream reader = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
            while (true) {

                String in = reader.readUTF();



                JSONObject request = new JSONObject();
                JSONParser parser = new JSONParser();
                try {
                    request = (JSONObject) parser.parse(in);
                } catch (Exception e) {
                    System.out.println("EXCEPTION");
                }
                JSONObject reply = new JSONObject();

                switch ((String) request.get("action")) {
                    case "Query" :
                        String meaning = dictionary.query((String) request.get("word"));
                        if (meaning != null) {
                            reply.put("meaning", meaning);
                        } else {
                            reply.put("error", "Word not found");
                        }




                        break;
                    case "Add" :
                        System.out.println("Add");

                        break;
                    case "Remove" :
                        System.out.println("Remove");

                        break;
                    case "Update" :
                        System.out.println("Update");

                        break;

                }
                writer.writeUTF(reply.toString());
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ConnectionThread(Socket client, Dictionary dictionary) {
        this.clientSocket = client;
        this.dictionary = dictionary;
    }
}
