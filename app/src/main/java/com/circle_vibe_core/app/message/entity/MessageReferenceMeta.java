package com.circle_vibe_core.app.message.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class MessageReferenceMeta {
	private Long threadId;
	private Long childThreadId;
	
	@NotBlank
	private Long chatId;
}
