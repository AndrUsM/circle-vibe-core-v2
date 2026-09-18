package com.circle_vibe_core.app.chatParticipant.service;

import java.util.Optional;

import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantCreateInputDto;
import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantUpdateInputDto;
import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;
import com.circle_vibe_core.app.chatParticipant.enums.ChatParticipantRole;

public class ChatParticipantCreateUpdatePayloadService {
	ChatParticipant toCreate(ChatParticipantCreateInputDto inputDto) {
		ChatParticipant chatParticipant = new ChatParticipant();
		chatParticipant.setChatId(inputDto.chatId);
		chatParticipant.setIsMuted(Optional.ofNullable(inputDto.isMuted).orElse(true));
		chatParticipant.setRole(Optional.ofNullable(inputDto.role).orElse(ChatParticipantRole.MEMBER));

		return chatParticipant;
	}

	ChatParticipant toUpdate(Long id, ChatParticipantUpdateInputDto inputDto) {
		ChatParticipant chatParticipant = new ChatParticipant();

		chatParticipant.setId(id);
		chatParticipant.setIsMuted(Optional.ofNullable(inputDto.isMuted).orElse(null));
		chatParticipant.setRole(Optional.ofNullable(inputDto.role).orElse(null));

		return chatParticipant;
	}
}
