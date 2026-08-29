package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static ServerSocket server;
    private static Map<Integer, ClientConnection> clients = new ConcurrentHashMap<>();
    private static int quantClients = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(1234);

        System.out.println("Server running on port 1234");

        while (true) {
            Socket socket = server.accept();

            Thread thread = new Thread(() -> {
                try {
                    handleClient(socket);
                } catch (IOException e) {
                    System.err.println("Error: "+e.getMessage());
                }
            });

            thread.start();
        }
    }

    private static void handleClient(Socket socket) throws IOException {
        ClientConnection client = new ClientConnection(quantClients, socket);

        clients.put(quantClients, client);

        quantClients++;
    }

}
