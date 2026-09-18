package com.circle_vibe_core.app.chatParticipant.entity;

import com.circle_vibe_core.app.chatParticipant.enums.ChatParticipantRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table (name = "chat_participant")
public class ChatParticipant {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotBlank
	@Column (name = "role")
	@Enumerated (EnumType.STRING)
	private ChatParticipantRole role = ChatParticipantRole.MEMBER;

	@NotBlank
	@Column (name = "is_muted")
	private Boolean isMuted;

	@NotBlank
	@Column (name = "user_id")
	private Long userId;

	@NotBlank
	@Column (name = "chat_id")
	private Long chatId;
}
