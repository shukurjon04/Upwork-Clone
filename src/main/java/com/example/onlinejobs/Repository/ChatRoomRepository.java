package com.example.onlinejobs.Repository;

import com.example.onlinejobs.Entity.Messaging.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    @Query("SELECT c FROM ChatRoom c WHERE c.senderId = :senderId AND c.receiverId = :receiverId")
    Optional<ChatRoom> findBySenderIdAndReceiverId(UUID senderId, UUID receiverId);
}
