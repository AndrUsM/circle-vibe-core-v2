package com.circle_vibe_core.app.chat.entity;

import com.circle_vibe_core.app.chat.enums.ChatType;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Embeddable 
public class ChatSettings {
	@NotNull
	@Column(name = "is_group_chat")
	private Boolean isGroupChat;

	@NotNull
	@Column(name = "users_limit")
	private Integer usersLimit;

	@NotNull
	@Column(name = "chat_type")
	@Enumerated(EnumType.STRING)
	private ChatType chatType = ChatType.PRIVATE;
}
