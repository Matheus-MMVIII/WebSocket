package com.websocket;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final int id;
    private final Socket socket;
    private final ClientManager clientManager;

    private final BufferedReader input;
    private final PrintWriter output;

    private String name;

    public ClientHandler(int id, Socket socket, ClientManager clientManager) throws IOException {

        this.id = id;
        this.socket = socket;
        this.clientManager = clientManager;

        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {

        try {
            send("What is your name?");

            String name = input.readLine();

            if (name == null) {
                return;
            }

            this.name = name;

            clientManager.addClient(this);

            send("Welcome, " + name + "!");
            send(clientManager.getClientsList(id));

            String message;

            while ((message = input.readLine()) != null) {

                Message parsedMessage =
                        Protocol.parse(message);

                handleMessage(parsedMessage);
            }

        } catch (IOException e) {

            System.out.println(
                    "Client " + id + " disconnected."
            );

        } finally {

            clientManager.removeClient(id);

            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    private void handleMessage(Message message) {

        switch (message.getType()) {

            case LIST:
                send(clientManager.getClientsList(id));
                break;

            case SEND:
                clientManager.sendMessage(
                        id,
                        message.getReceiverId(),
                        message.getContent()
                );
                break;

            case BROADCAST:
                clientManager.broadcast(
                        id,
                        message.getContent()
                );
                break;

            case QUIT:
                closeConnection();
                break;

            case UNKNOWN:
                send("Unknown command.");
                break;
        }
    }

    public void send(String message) {
        output.println(message);
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
