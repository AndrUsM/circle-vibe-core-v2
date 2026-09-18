package com.circle_vibe_core.app.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Embeddable 
public class ChatMeta {
	@NotNull  
	@Column(name = "is_active")
	public Boolean isActive;

	@Column (name = "block_reason")
	public String blockReason;

	@NotNull 
	@Column(name = "is_removed")
	public Boolean isRemoved;

	@NotNull 
	public String name;

	@NotNull
	public String description;
}
