package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
