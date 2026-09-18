package com.circle_vibe_core.app.chatParticipant.dto;

import com.circle_vibe_core.app.chatParticipant.enums.ChatParticipantRole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ChatParticipantFilterParams {
	public Boolean isMuted;
	public ChatParticipantRole role;

	@NotBlank 
	public Long userId;
	
	@NotBlank 
	public Long chatId;
}
