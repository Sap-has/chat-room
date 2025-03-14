package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChatMessage {
    @NotEmpty(message = "Sender cannot be empty")
    private String sender;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @NotEmpty(message = "Room code cannot be empty")
    private String roomCode;

    @NotNull(message = "Message type cannot be null")
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    // Getters and setters
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", type=" + type +
                '}';
    }
}