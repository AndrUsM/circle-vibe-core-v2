package com.circle_vibe_core.app.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserContactsInputDto(
    @NotBlank String email,
    @NotBlank String phone
){}