package com.websocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int nextClientId = 1;

    public static void main(String[] args) {

        ClientManager clientManager = new ClientManager();

        try (ServerSocket server = new ServerSocket(1234)) {

            System.out.println("Server running on port " + server.getLocalPort());

            while (true) {

                Socket socket = server.accept();

                int clientId = nextClientId++;

                ClientHandler handler = new ClientHandler(clientId, socket, clientManager);

                Thread thread = new Thread(handler, "Client-" + clientId);

                thread.start();
            }

        } catch (IOException e) {

            System.out.println("Server error: " + e.getMessage());
        }
    }
}
