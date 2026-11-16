package com.circle_vibe_core.app.user.dto;

import com.circle_vibe_core.app.user.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesOutputDto {
	private UserRole role;
}
