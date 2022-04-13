package server;

import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread {

    private Socket clientSocket;


    @Override
    public void run() {
        try {
            DataInputStream reader = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());






        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ConnectionThread(Socket client) {
        this.clientSocket = client;
    }
}
