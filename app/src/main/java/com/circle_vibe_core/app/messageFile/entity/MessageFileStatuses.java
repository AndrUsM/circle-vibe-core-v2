package com.circle_vibe_core.app.messageFile.entity;

import com.circle_vibe_core.app.messageFile.enums.MessageFileEntityType;
import com.circle_vibe_core.app.messageFile.enums.MessageFileType;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class MessageFileStatuses {
	@NotNull
	@Enumerated(EnumType.STRING)
	private MessageFileType type;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "entity_type")
	private MessageFileEntityType entityType;
}
