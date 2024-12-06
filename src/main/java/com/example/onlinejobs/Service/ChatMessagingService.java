package com.example.onlinejobs.Service;

import com.example.onlinejobs.Dtos.MessageDto;
import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Messaging.Message;
import com.example.onlinejobs.Repository.ChatMessageRepository;
import com.example.onlinejobs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatMessagingService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;

    @Autowired
    public ChatMessagingService(ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomService = chatRoomService;
        this.userRepository = userRepository;
    }

    public Message saveMessage(MessageDto message) {
        var chatId = chatRoomService.getChatRoomId(message.getSenderId(), message.getReceiverId()).orElse(null);
        UserJobs sender = userRepository.findById(message.getSenderId()).orElseThrow(() -> new RuntimeException("Sender not found"));
        UserJobs receiver = userRepository.findById(message.getReceiverId()).orElseThrow(() -> new RuntimeException("Receiver not found"));
        Message message1 = new Message();
        message1.setChatId(chatId);
        message1.setSender(sender);
        message1.setReceiver(receiver);
        message1.setMessage(message.getMessage());
        return chatMessageRepository.save(message1);
    }

    public List<Message> findChatMessages(UUID senderId, UUID receiverId) {
        var chatId = chatRoomService.getChatRoomId(senderId, receiverId);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
