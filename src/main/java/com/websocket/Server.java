package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static ServerSocket server;
    private static Map<Integer, String> clientMap;
    private static int quantClients = 1;

    public static void main(String args[]) throws IOException {
        try {
            server = new ServerSocket(1234);
            clientMap = new HashMap<>();

            while (true) {
                System.out.println("Server Port: "+server.getLocalPort());
                Socket client = server.accept();
                System.out.println("Client Connect: " + client.getInetAddress().getHostAddress());

                OutputStream output = client.getOutputStream();
                InputStream input = client.getInputStream();

                output.write("Hi, whats is your name?".getBytes());
                output.flush();

                byte[] bufferName = new byte[1024];

                int dadoName = input.read(bufferName);

                String messageName = new String(bufferName, 0, dadoName);

                System.out.println("name: "+messageName+" id: "+quantClients);

                clientMap.put(quantClients, messageName);

                quantClients++;

                String list = "";

                int responseChose;

                do {
                    list = "What do you need connection? \n-1 - to repeat\n" + getConnections(quantClients-1);

                    output.write(list.getBytes());
                    output.flush();

                    byte[] bufferResponseChose = new byte[1024];

                    int dadoResponseChose = input.read(bufferResponseChose);

                    String messageResponseChose = new String(bufferResponseChose, 0, dadoResponseChose);

                    responseChose = Integer.valueOf(messageResponseChose);

                    System.out.println(responseChose);

                } while (responseChose != -1);

                output.close();
                input.close();
                client.close();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        } finally {

        }
    }

    public static String getConnections(int ignore) {
        String connections = "";
        for (int i = 1; i < clientMap.size()+1; i++) {
            if (i == ignore)
                continue;
            connections += i + " - " + clientMap.get(i) + "\n";
        }
        return connections;
    }
}
