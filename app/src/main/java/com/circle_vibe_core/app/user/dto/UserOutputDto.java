package com.circle_vibe_core.app.user.dto;

import java.time.LocalDate;

import com.circle_vibe_core.app.user.entity.UserAvatar;
import com.circle_vibe_core.app.user.entity.UserContacts;
import com.circle_vibe_core.app.user.entity.UserLocation;
import com.circle_vibe_core.app.user.entity.UserPrivacySettings;
import com.circle_vibe_core.app.user.entity.UserRoles;
import com.circle_vibe_core.app.user.entity.UserStatuses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputDto {
    private long id;
    private String name;
    private LocalDate birthDate;
    private UserPrivacySettings privacySettings;
    private UserLocation location;
    private UserAvatar avatar;
	private UserContacts userContacts;
    private UserStatuses userStatuses;
	private UserRoles userRole;
}