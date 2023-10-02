package com.example;

import java.io.*;
import java.net.*;

public class Client {
    Socket socket;
    String ipServer = "127.0.0.1";
    int portServer = 6666;
    BufferedReader keyboard;
    BufferedReader serverResponse;
    DataOutputStream output;

    String userString;
    String receivedString;

    public Socket connetti() {
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));

            socket = new Socket(ipServer, portServer);

            output = new DataOutputStream(socket.getOutputStream());
            serverResponse = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR at starting connection");
            System.exit(1);
        }
        return socket;
    }

    public void send() {
        try {
            System.out.println("Sending message...");

            System.out.println("Waiting for user input...");
            userString = keyboard.readLine();


            output.writeBytes(userString + "\n");

            receivedString = serverResponse.readLine();

            System.out.println("SERVER CLAIMS: " + receivedString);

            socket.close();
        } catch (Exception e) {
            System.err.println("ERROR AT SENDING MESSAGE.QUITING...");
            System.exit(1);
        }
    }
}
