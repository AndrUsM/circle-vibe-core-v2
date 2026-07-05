package com.circle_vibe_core.app.user.dto;

import com.circle_vibe_core.app.user.enums.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

record CreateUserPrivacySettingsInputDto(
    @NotBlank Boolean isHiddenContactInfo,
    @NotBlank Boolean isAllowedToSearch
){}

record CreateUserLocationInputDto (
    @NotBlank String country,
    @NotBlank String ciry
) {}

record CreateUserContactsInputDto(
    @NotBlank String email,
    @NotBlank String phone
){}

public record CreateUserInputDto(
    @NotBlank String name,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String avatarUrl,
    @NotNull CreateUserPrivacySettingsInputDto privacySettings,
    @NotNull CreateUserLocationInputDto location,
    @NotNull CreateUserContactsInputDto contacts,
    @NotBlank UserType userType
) {}
