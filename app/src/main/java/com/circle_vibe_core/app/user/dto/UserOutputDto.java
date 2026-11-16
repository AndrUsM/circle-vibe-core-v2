package com.circle_vibe_core.app.user.dto;

import java.time.LocalDate;

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
    private UserPrivacySettingsOutputDto privacySettings;
    private UserLocationOutputDto location;
    private UserAvatarOutputDto avatar;
	private UserContactsOutputDto userContacts;
    private UserStatusesOutputDto userStatuses;
	private UserRolesOutputDto userRole;
}