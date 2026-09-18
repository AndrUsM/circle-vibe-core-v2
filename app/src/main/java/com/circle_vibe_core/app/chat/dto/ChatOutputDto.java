package com.circle_vibe_core.app.chat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor
@NoArgsConstructor
public class ChatOutputDto {
	public Long id;
	public String avatarUrl;
	public ChatMetaOutputDto chatMeta;
	public ChatSettingsOutputDto chatSettings;
	public ChatSecurityOutputDto chatSecurity;
	public LocalDateTime updatedAt;
}
