package com.example.onlinejobs.Service;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import com.example.onlinejobs.Entity.Messaging.ChatRoom;
import com.example.onlinejobs.Repository.ChatRoomRepository;
import com.example.onlinejobs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    public Optional<UUID> getChatRoomId(UUID senderId, UUID receiverId) {
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId).map(ChatRoom::getChatId).or(() ->{
            if (chatRoomRepository.findBySenderIdAndReceiverId(receiverId, senderId).isEmpty() ){
                var chatId = createChatRoom(senderId, receiverId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });
    }

    private UUID createChatRoom(UUID senderId, UUID receiverId) {

        var chatId = UUID.randomUUID();

        UserJobs sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("user not found"));
        UserJobs recipient = userRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("user not found"));
        ChatRoom senderRecipient = new ChatRoom();
        senderRecipient.setSenderId(sender);
        senderRecipient.setId(chatId);
        senderRecipient.setReceiverId(recipient);

        ChatRoom recipientSender = new ChatRoom();
        recipientSender.setSenderId(recipient);
        recipientSender.setId(chatId);
        recipientSender.setReceiverId(sender);

        chatRoomRepository.save(recipientSender);
        chatRoomRepository.save(senderRecipient);

        return chatId;
    }
}
