package com.circle_vibe_core.app.message.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class MessageVisibilityRulesInputDto {
	@NotNull
	public List<Long> allowedUserIds;

	@NotNull
	public List<Long> restrictedUserIds;

	@NotNull
	public Boolean enabled;

	@NotNull
	public Boolean hidden;
}
