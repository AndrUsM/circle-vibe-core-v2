package com.circle_vibe_core.app.user.entity;

import com.circle_vibe_core.app.user.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserRoles {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
    @Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRole role = UserRole.USER;
}
