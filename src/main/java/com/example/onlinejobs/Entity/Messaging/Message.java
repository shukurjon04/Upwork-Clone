package com.example.onlinejobs.Entity.Messaging;

import com.example.onlinejobs.Entity.ControlFunctionTable.AuditTable;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Message extends AuditTable {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID chatId;
    @ManyToOne
    private UserJobs sender;
    @ManyToOne
    private UserJobs receiver;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String message;
    public Message(UUID id,  String message) {
        this.id = id;
        this.message = message;
    }

    public Message(UUID id, UUID chatId, UserJobs sender, UserJobs receiver, MessageType type, String message) {
        this.id = id;
        this.chatId = chatId;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.message = message;
    }



    public UUID getChatId() {
        return chatId;
    }

    public void setChatId(UUID chatId) {
        this.chatId = chatId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Message() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserJobs getSender() {
        return sender;
    }

    public void setSender(UserJobs sender) {
        this.sender = sender;
    }

    public UserJobs getReceiver() {
        return receiver;
    }

    public void setReceiver(UserJobs receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
