package com.circle_vibe_core.app.chatParticipant.dto;

import com.circle_vibe_core.app.chatParticipant.enums.ChatParticipantRole;

import jakarta.annotation.Nullable;

public class ChatParticipantUpdateInputDto {
	@Nullable
	public ChatParticipantRole role;

	@Nullable
	public Boolean isMuted;
}
