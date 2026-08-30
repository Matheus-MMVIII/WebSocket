package com.websocket;

public class Protocol {
    public static Message parse(String input) {

        if (input == null || input.isBlank()) {
            return new Message(Message.Type.UNKNOWN);
        }

        String[] parts = input.trim().split(" ", 3);

        String command = parts[0].toUpperCase();

        switch (command) {

            case "LIST":
                return new Message(Message.Type.LIST);

            case "QUIT":
                return new Message(Message.Type.QUIT);

            case "BROADCAST":

                if (parts.length < 2) {
                    return new Message(Message.Type.UNKNOWN);
                }

                return new Message(
                        Message.Type.BROADCAST, -1,
                        input.substring("BROADCAST ".length())
                );

            case "SEND":

                if (parts.length < 3) {
                    return new Message(Message.Type.UNKNOWN);
                }

                try {

                    int receiverId = Integer.parseInt(parts[1]);

                    String content = parts[2];

                    return new Message(Message.Type.SEND, receiverId, content);

                } catch (NumberFormatException e) {

                    return new Message(Message.Type.UNKNOWN);
                }

            default:
                return new Message(Message.Type.UNKNOWN);
        }
    }
}
