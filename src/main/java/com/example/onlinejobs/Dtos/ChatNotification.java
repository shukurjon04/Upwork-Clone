package com.example.onlinejobs.Dtos;

import java.util.UUID;

public class ChatNotification {
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private String message;

    public ChatNotification(UUID id, UUID senderId, UUID receiverId, String message) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    public ChatNotification() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
