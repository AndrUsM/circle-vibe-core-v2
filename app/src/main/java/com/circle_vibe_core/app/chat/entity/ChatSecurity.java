package com.circle_vibe_core.app.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Embeddable 
public class ChatSecurity {
	@NotBlank 
	@Column(name = "encryption_secret")
	public String encryptionSecret;
}
