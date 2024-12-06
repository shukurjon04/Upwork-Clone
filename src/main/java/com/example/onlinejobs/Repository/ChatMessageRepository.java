package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Messaging.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByChatId(UUID uuid);
}
