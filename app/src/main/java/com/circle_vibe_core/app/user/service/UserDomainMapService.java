package com.circle_vibe_core.app.user.service;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.dto.UserAvatarOutputDto;
import com.circle_vibe_core.app.user.dto.UserContactsOutputDto;
import com.circle_vibe_core.app.user.dto.UserLocationOutputDto;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.dto.UserPrivacySettingsOutputDto;
import com.circle_vibe_core.app.user.dto.UserRolesOutputDto;
import com.circle_vibe_core.app.user.dto.UserStatusesOutputDto;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.entity.UserAvatar;
import com.circle_vibe_core.app.user.entity.UserContacts;
import com.circle_vibe_core.app.user.entity.UserLocation;
import com.circle_vibe_core.app.user.entity.UserPrivacySettings;
import com.circle_vibe_core.app.user.entity.UserRoles;
import com.circle_vibe_core.app.user.entity.UserStatuses;

@Service
public class UserDomainMapService {
	public UserOutputDto toUser(User user) {
		UserOutputDto outputDto = new UserOutputDto();

		UserRolesOutputDto userRoles = this.getUserRoles(user.getUserRoles());
		UserAvatarOutputDto userAvatar = this.getUserAvatarOutputDto(user.getAvatar());
		UserLocationOutputDto userLocation = this.getUserLocationOutputDto(user.getLocation());
		UserPrivacySettingsOutputDto userPrivacySettings = this
				.getUserPrivacySettingsOutputDto(user.getPrivacySettings());
		UserContactsOutputDto userContacts = this.getUserContactsOutputDto(user.getContacts());
		UserStatusesOutputDto userStatuses = this.getUserStatusesOutputDto(user.getUserStatuses());

		outputDto.setAvatar(userAvatar);
		outputDto.setUserRole(userRoles);
		outputDto.setLocation(userLocation);
		outputDto.setPrivacySettings(userPrivacySettings);
		outputDto.setUserContacts(userContacts);
		outputDto.setUserStatuses(userStatuses);
		outputDto.setBirthDate(user.getBirthDate());
		outputDto.setId(user.getId());
		outputDto.setName(user.getName());

		return outputDto;
	}

	private UserRolesOutputDto getUserRoles(UserRoles roles) {
		return new UserRolesOutputDto(roles.getRole());
	}

	private UserAvatarOutputDto getUserAvatarOutputDto(UserAvatar userAvatar) {
		return new UserAvatarOutputDto(userAvatar.getUrl(), userAvatar.getOptimizedUrl());
	}

	private UserLocationOutputDto getUserLocationOutputDto(UserLocation userLocation) {
		return new UserLocationOutputDto(userLocation.getCountryCode(), userLocation.getCity());
	}

	private UserPrivacySettingsOutputDto getUserPrivacySettingsOutputDto(UserPrivacySettings userPrivacySettings) {
		return new UserPrivacySettingsOutputDto(userPrivacySettings.getId(),
				userPrivacySettings.getIsHiddenContactInfo(), userPrivacySettings.getIsAllowedToSearch());
	}

	private UserContactsOutputDto getUserContactsOutputDto(UserContacts userContacts) {
		return new UserContactsOutputDto(userContacts.getEmail(), userContacts.getPhone());
	}

	private UserStatusesOutputDto getUserStatusesOutputDto(UserStatuses userStatuses) {
		return new UserStatusesOutputDto(userStatuses.getId(), userStatuses.getChatStatus(), userStatuses.getStatus(), userStatuses.getAccountStatus());
	}
}
