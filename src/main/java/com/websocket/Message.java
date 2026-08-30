package com.websocket;

public class Message {

    public enum Type {
        LIST,
        SEND,
        BROADCAST,
        QUIT,
        UNKNOWN
    }

    private final Type type;
    private final int receiverId;
    private final String content;

    public Message(Type type, int receiverId, String content) {
        this.type = type;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Message(Type type) {
        this(type, -1, "");
    }

    public Type getType() {
        return type;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }
}

