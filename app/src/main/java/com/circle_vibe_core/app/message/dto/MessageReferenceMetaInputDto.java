package com.circle_vibe_core.app.message.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class MessageReferenceMetaInputDto {
	@Nullable 
	public Long threadId;

	@Nullable
	public Long childThreadId;

	@NotNull
	public Long chatId;
}
