package com.example.onlinejobs.Controller.ChatContr;

import com.example.onlinejobs.Dtos.ChatNotification;
import com.example.onlinejobs.Dtos.MessageDto;
import com.example.onlinejobs.Entity.Messaging.Message;
import com.example.onlinejobs.Service.ChatMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessagingService chatMessagingService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessagingService chatMessagingService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessagingService = chatMessagingService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDto message) {
        Message savedMsg = chatMessagingService.saveMessage(message);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiverId()), "queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSender().getId(),
                        savedMsg.getReceiver().getId(),
                        savedMsg.getMessage()
                )
        );
    }

    @GetMapping("/message/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("senderId") UUID senderId,
                                                     @PathVariable("recipientId") UUID recipientId) {
          return ResponseEntity.ok(chatMessagingService.findChatMessages(senderId, recipientId));
    }

}
