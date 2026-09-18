package com.circle_vibe_core.app.chat.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateChatSecretInputDto(
	@NotNull String encryptionSecret
) {}
