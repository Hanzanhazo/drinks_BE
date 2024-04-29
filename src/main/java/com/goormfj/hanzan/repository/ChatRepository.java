package com.goormfj.hanzan.repository;

import com.goormfj.hanzan.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByRoomId(String roomId);
}
