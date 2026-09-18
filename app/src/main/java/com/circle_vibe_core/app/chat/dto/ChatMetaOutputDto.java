package com.circle_vibe_core.app.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class ChatMetaOutputDto {
	public Boolean isActive;
	public String blockReason;
	public Boolean isRemoved;
	public String name;
	public String description;
}
