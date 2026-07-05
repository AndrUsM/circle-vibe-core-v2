package com.circle_vibe_core.app.user.dto;

public record UserByTokenParams(
    Boolean active,
    String token
) {}
