package com.circle_vibe_core.app.message.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.circle_vibe_core.app.message.enums.MessageStatus;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private MessageStatus status;

	@NotBlank
	private Long uploadingProgress;

	@Embedded
	@Valid
	private MessageReferenceMeta referenceMeta;

	@Embedded
	@Valid
	private MessageVisibilityRules visibilityRules;

	@NotBlank
	private Long userId;

	@NotNull
	private List<Long> fileIds;

	@NotBlank
	private Boolean removed;

	@NotBlank
	private LocalDateTime updatedAt;
}
