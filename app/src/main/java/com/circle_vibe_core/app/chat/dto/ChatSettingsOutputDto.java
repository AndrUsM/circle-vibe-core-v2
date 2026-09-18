package com.circle_vibe_core.app.chat.dto;

import com.circle_vibe_core.app.chat.enums.ChatType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor
@NoArgsConstructor 
public class ChatSettingsOutputDto {
	public Boolean isGroupChat;
	public Integer usersLimit;
	public ChatType chatType;
}
