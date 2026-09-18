package com.circle_vibe_core.app.chat.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class ChatInputDto {
	@Nullable
	public String avatarUrl;

	@Nullable
	public ChatSettingsInputDto chatSettings;

	@NotBlank 
	public String name;

	@NotBlank
	public String description;
}
