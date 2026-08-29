package com.websocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1234);

            Scanner sc = new Scanner(System.in);

            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            byte[] inputBuffer = new byte[1024];

            int dado = input.read(inputBuffer);

            String messageReceive = new String(inputBuffer, 0, dado);

            System.out.println(messageReceive);

            String message = sc.nextLine();

            output.write(message.getBytes());

            byte[] inputBuffer2 = new byte[1024];

            int dado2 = input.read(inputBuffer2);

            String messageReceive2 = new String(inputBuffer2, 0, dado2);

            System.out.println(messageReceive2);

            String message2 = sc.next();

            output.write(message2.getBytes());

            output.close();
            input.close();
            clientSocket.close();

            System.out.println("Conexão encerrada");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
