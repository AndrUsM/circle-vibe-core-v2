package com.circle_vibe_core.app.chat.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table (name = "chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Nullable
	@Column(name = "avatar_url")
	private String avatarUrl;

	@Nullable
	@Column(name = "bucket")
	private String bucket;

	@NotNull 
	@Embedded
	@Valid 
	@Column(name = "chat_settings")
	private ChatSettings chatSettings;

	@NotNull 
	@Embedded 
	@Valid
	@Column(name = "chat_meta")
	public ChatMeta chatMeta;

	@NotNull 
	@Embedded 
	@Valid
	@Column(name = "chat_security")
	public ChatSecurity chatSecurity;

	@NotNull 
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
