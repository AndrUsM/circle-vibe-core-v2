package com.circle_vibe_core.app.chat.dto;
import com.circle_vibe_core.app.chat.enums.ChatType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor
public class ChatSettingsInputDto {
	public Boolean isGroupChat;
	public Integer usersLimit;
	public ChatType chatType;
}
