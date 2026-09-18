package com.circle_vibe_core.app.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor
@NoArgsConstructor 
public class ChatSecurityOutputDto {
	public String encryptionSecret;
}
