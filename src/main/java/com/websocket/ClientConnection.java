package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnection {
    private final int ID;
    private final Socket SOCKET;
    private final InputStream INPUT;
    private final OutputStream OUTPUT;
    private String name;

    public ClientConnection(int id, Socket socket) throws IOException {
        ID = id;
        SOCKET = socket;
        INPUT = SOCKET.getInputStream();
        OUTPUT = SOCKET.getOutputStream();
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String receive() throws IOException {
        byte[] buffer = new byte[1024];

        int bytesRead = INPUT.read(buffer);

        if (bytesRead == -1) {
            return null;
        }

        return new String(buffer, 0, bytesRead);
    }

    public void send(String message) throws IOException {
        OUTPUT.write(message.getBytes());
        OUTPUT.flush();
    }

    public void close() throws IOException {
        SOCKET.close();
    }
}
