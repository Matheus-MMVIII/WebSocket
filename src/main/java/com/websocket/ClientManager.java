package com.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {

    private final Map<Integer, ClientHandler> clients = new ConcurrentHashMap<>();

    public void addClient(ClientHandler client) {

        clients.put(client.getId(), client);

        System.out.println(
                "Client connected: "
                        + client.getId()
                        + " - "
                        + client.getName()
        );
    }

    public void removeClient(int id) {

        ClientHandler removed = clients.remove(id);

        if (removed != null) {

            System.out.println(
                    "Client removed: "
                            + removed.getId()
                            + " - "
                            + removed.getName()
            );
        }
    }

    public ClientHandler getClient(int id) {
        return clients.get(id);
    }

    public String getClientsList(int ignoreId) {

        StringBuilder result = new StringBuilder();

        result.append("Connected clients:\n");

        for (ClientHandler client : clients.values()) {

            if (client.getId() == ignoreId) {
                continue;
            }

            result.append("- ")
                    .append(client.getId())
                    .append(" - ")
                    .append(client.getName())
                    .append("\n");
        }

        return result.toString();
    }

    public void sendMessage(int senderId, int receiverId, String content) {

        ClientHandler receiver = clients.get(receiverId);

        ClientHandler sender = clients.get(senderId);

        if (receiver == null) {

            sender.send("Client " + receiverId + " not found.");

            return;
        }

        receiver.send(
                "["
                        + sender.getName()
                        + "] "
                        + content
        );
    }

    public void broadcast(int senderId, String content) {

        ClientHandler sender = clients.get(senderId);

        if (sender == null) {
            return;
        }

        String message =
                "["
                        + sender.getName()
                        + "] "
                        + content;

        for (ClientHandler client : clients.values()) {

            if (client.getId() == senderId) {
                continue;
            }

            client.send(message);
        }
    }
}
