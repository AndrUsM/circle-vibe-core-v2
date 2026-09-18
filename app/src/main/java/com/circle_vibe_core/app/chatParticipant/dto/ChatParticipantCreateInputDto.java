package com.circle_vibe_core.app.chatParticipant.dto;

import com.circle_vibe_core.app.chatParticipant.enums.ChatParticipantRole;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class ChatParticipantCreateInputDto {
	@Nullable
	public ChatParticipantRole role;

	@Nullable
	public Boolean isMuted;

	@NotBlank
	public Long userId;

	@NotBlank
	public Long chatId;
}
