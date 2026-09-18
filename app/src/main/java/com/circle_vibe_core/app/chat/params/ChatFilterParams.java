package com.circle_vibe_core.app.chat.params;

import com.circle_vibe_core.app.chat.enums.ChatType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor 
public class ChatFilterParams {
	public String name;
	public String description;
	public Boolean isGroupChat;
	public ChatType chatType;
}
