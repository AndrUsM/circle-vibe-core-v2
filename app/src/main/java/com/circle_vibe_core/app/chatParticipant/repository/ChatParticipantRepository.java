package com.circle_vibe_core.app.chatParticipant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
	
}
