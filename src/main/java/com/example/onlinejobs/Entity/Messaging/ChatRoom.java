package com.example.onlinejobs.Entity.Messaging;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID chatId;
    @ManyToOne
    private UserJobs senderId;
    @ManyToOne
    private UserJobs receiverId;

    public ChatRoom() {
    }

    public ChatRoom(UUID id, UUID chatId, UserJobs senderId, UserJobs receiverId) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public UUID getChatId() {
        return chatId;
    }

    public void setChatId(UUID chatId) {
        this.chatId = chatId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserJobs getSenderId() {
        return senderId;
    }

    public void setSenderId(UserJobs senderId) {
        this.senderId = senderId;
    }

    public UserJobs getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserJobs receiverId) {
        this.receiverId = receiverId;
    }
}
