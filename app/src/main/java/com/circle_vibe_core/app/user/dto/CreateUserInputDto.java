package com.circle_vibe_core.app.user.dto;

import java.time.LocalDate;

import com.circle_vibe_core.app.user.enums.UserRole;
import com.circle_vibe_core.app.user.enums.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserInputDto(
    @NotBlank String name,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String avatarUrl,
	@NotBlank LocalDate birthDate,
    @NotNull CreateUserPrivacySettingsInputDto privacySettings,
    @NotNull CreateUserLocationInputDto location,
    @NotNull CreateUserContactsInputDto contacts,
    @NotBlank UserType userType,
	UserRole userRole
) {}
