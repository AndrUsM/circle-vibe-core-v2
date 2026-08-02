package com.circle_vibe_core.app.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserLocationInputDto (
    @NotBlank String countryCode,
    @NotBlank String city
) {}