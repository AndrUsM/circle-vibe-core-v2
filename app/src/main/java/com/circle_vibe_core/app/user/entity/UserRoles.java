package com.circle_vibe_core.app.user.entity;

import com.circle_vibe_core.app.user.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserRoles {
	@NotNull
    @Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRole role = UserRole.USER;
}
