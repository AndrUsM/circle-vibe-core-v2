package com.circle_vibe_core.app.user.dto;

import java.time.LocalDate;

import com.circle_vibe_core.app.user.enums.UserRole;
import com.circle_vibe_core.app.user.enums.UserType;

import jakarta.validation.constraints.NotNull;

public record CreateUserInputDto(
    @NotNull String name,
    @NotNull String username,
    @NotNull String password,
    @NotNull String avatarUrl,
	@NotNull LocalDate birthDate,
    @NotNull CreateUserPrivacySettingsInputDto privacySettings,
    @NotNull CreateUserLocationInputDto location,
    @NotNull CreateUserContactsInputDto contacts,
    @NotNull UserType userType,
	@NotNull UserRole userRole
) {}
