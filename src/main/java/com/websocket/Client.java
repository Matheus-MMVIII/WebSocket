package com.websocket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 1234);

                BufferedReader input = new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream(),
                                        StandardCharsets.UTF_8
                                )
                        );

                PrintWriter output = new PrintWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream(),
                                        StandardCharsets.UTF_8
                                ),
                                true
                        );

                Scanner scanner = new Scanner(System.in)
        ) {

            // =========================
            // LOGIN
            // =========================

            String question = input.readLine();

            System.out.println(question);

            String name = scanner.nextLine();

            output.println(name);


            // =========================
            // THREAD OF RECEIVE
            // =========================

            Thread receiveThread = new Thread(() -> {

                try {

                    String message;

                    while ((message = input.readLine()) != null) {

                        System.out.println("\n" + message);

                        System.out.print("> ");
                    }

                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }

            });

            receiveThread.start();


            // =========================
            // SENDS MESSAGES
            // =========================

            while (true) {

                System.out.print("> ");

                String message = scanner.nextLine();

                output.println(message);

                if (message.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

        } catch (IOException e) {

            System.out.println("Connection error: " + e.getMessage());
        }

        System.out.println("Connection closed.");
    }
}