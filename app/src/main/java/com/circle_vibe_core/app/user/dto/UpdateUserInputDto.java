package com.circle_vibe_core.app.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.circle_vibe_core.app.user.enums.UserRole;

public record UpdateUserInputDto(
  String name,
  String email,
  String phone,
  LocalDate birthDate,
  List<Long> blockedUserIds,
  Boolean isHiddenContactInfo,
  Boolean isAllowedToSearch,
  String countryCode,
  String city,
  String avatarUrl,
  UserRole userRole
) {}
