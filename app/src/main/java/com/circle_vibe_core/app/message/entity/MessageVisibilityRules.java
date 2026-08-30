package com.circle_vibe_core.app.message.entity;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class MessageVisibilityRules {
	@NotBlank
	private List<Long> allowedUserIds;
	
	@NotBlank
	private List<Long> restrictedUserIds;

	@NotBlank
	private Boolean enabled;

	@NotBlank
	private Boolean hidden;
}
