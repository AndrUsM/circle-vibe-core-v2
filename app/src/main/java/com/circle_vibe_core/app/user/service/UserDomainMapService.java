package com.circle_vibe_core.app.user.service;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.dto.UserOutputDto;
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
		
		UserRoles userRoles = user.getUserRoles();
		UserAvatar userAvatar = user.getAvatar();
		UserLocation userLocation = user.getLocation();
		UserPrivacySettings userPrivacySettings = user.getPrivacySettings();
		UserContacts userContacts = user.getContacts();
		UserStatuses userStatuses = user.getUserStatuses();
		
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
}
