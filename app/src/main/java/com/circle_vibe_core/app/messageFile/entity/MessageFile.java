package com.circle_vibe_core.app.messageFile.entity;

import com.circle_vibe_core.app.messageFile.enums.MessageFileEntityType;
import com.circle_vibe_core.app.messageFile.enums.MessageFileType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "message_files")
public class MessageFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Column(name = "file_name")
	private String fileName;

	@NotBlank
	private String url;

	@NotBlank
	@Column(name = "optimized_url")
	private String optimizedUrl;

	@NotNull
	@Embedded
	@Valid
	private MessageFileStatuses statuses;

	@NotBlank
	private Long messageId;
}
