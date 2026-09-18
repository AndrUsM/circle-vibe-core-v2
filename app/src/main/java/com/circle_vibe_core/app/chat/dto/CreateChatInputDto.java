package com.circle_vibe_core.app.chat.dto;

import com.circle_vibe_core.app.chat.enums.ChatType;

import jakarta.validation.constraints.NotNull;

public record CreateChatInputDto(
	@NotNull String name,
	@NotNull String description,
	@NotNull Boolean isGroupChat,
	@NotNull Integer usersLimit,
	@NotNull ChatType chatType,
	@NotNull String secretPhrase
) {}
