package com.circle_vibe_core.app.user.dto;
import jakarta.validation.constraints.NotBlank;

public record CreateUserPrivacySettingsInputDto(
    @NotBlank Boolean isHiddenContactInfo,
    @NotBlank Boolean isAllowedToSearch
){}