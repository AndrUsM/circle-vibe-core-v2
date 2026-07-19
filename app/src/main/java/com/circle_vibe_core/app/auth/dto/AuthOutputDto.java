package com.circle_vibe_core.app.auth.dto;

import com.circle_vibe_core.app.user.dto.UserOutputDto;

public record AuthOutputDto(String token, UserOutputDto user) {}
