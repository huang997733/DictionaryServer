package server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
                String word;
                String meaning;
                switch ((String) request.get("action")) {
                    case "Query" :
                        word = (String) request.get("word");
                        meaning = dictionary.query((String) request.get("word"));
                        if (meaning != null) {
                            reply.put("meaning", meaning);
                        } else if (word.equals("")){
                            reply.put("error", "Please enter a word");
                        } else {
                            reply.put("error", "Word not found");
                        }

                        break;
                    case "Add" :
                        word = (String) request.get("word");
                        meaning =  (String) request.get("meaning");
                        if (word.equals("")) {
                            reply.put("error", "Please enter a word");
                        } else if (meaning.equals("")) {
                            reply.put("error", "Please enter the meaning");
                        } else {
                            boolean addSuccess = dictionary.add(word, meaning);
                            if (addSuccess) {
                                reply.put("msg", "Add success");
                            } else {
                                reply.put("error", "Add fail: word already exists");
                            }
                        }

                        break;
                    case "Remove" :
                        word = (String) request.get("word");
                        if (word.equals("")) {
                            reply.put("error", "Please enter a word");
                        } else {
                            boolean removeSuccess = dictionary.remove(word);
                            if (removeSuccess) {
                                reply.put("msg", "Remove success");
                            } else {
                                reply.put("error", "Remove fail: word not found");
                            }
                        }
                        break;
                    case "Update" :
                        word = (String) request.get("word");
                        meaning =  (String) request.get("meaning");
                        if (word.equals("")) {
                            reply.put("error", "Please enter a word");
                        } else if (meaning.equals("")) {
                            reply.put("error", "Please enter the meaning");
                        } else {
                            boolean updateSuccess = dictionary.update(word, meaning);
                            if (updateSuccess) {
                                reply.put("msg", "Update success");
                            } else {
                                reply.put("error", "Update fail: word does not exist");
                            }
                        }

                        break;

                }
                writer.writeUTF(reply.toString());
                writer.flush();
            }

        } catch (IOException e) {
            System.out.println("A client has disconnected");
        }
    }


    public ConnectionThread(Socket client, Dictionary dictionary) {
        this.clientSocket = client;
        this.dictionary = dictionary;
    }
}
