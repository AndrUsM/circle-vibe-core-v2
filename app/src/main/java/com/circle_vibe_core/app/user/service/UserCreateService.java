package com.circle_vibe_core.app.user.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.shared.service.HashService;
import com.circle_vibe_core.app.user.dto.CreateUserInputDto;
import com.circle_vibe_core.app.user.dto.CreateUserLocationInputDto;
import com.circle_vibe_core.app.user.dto.CreateUserPrivacySettingsInputDto;
import com.circle_vibe_core.app.user.dto.UpdateUserInputDto;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.entity.UserAvatar;
import com.circle_vibe_core.app.user.entity.UserContacts;
import com.circle_vibe_core.app.user.entity.UserLocation;
import com.circle_vibe_core.app.user.entity.UserPrivacySettings;
import com.circle_vibe_core.app.user.entity.UserRoles;
import com.circle_vibe_core.app.user.entity.UserSessionMeta;
import com.circle_vibe_core.app.user.entity.UserTokens;

@Service
public class UserCreateService {
	private final HashService hashService;

	public UserCreateService(HashService hashService) {
		this.hashService = hashService;
	}

	public User getCreatedUser(CreateUserInputDto userDto) {
		User user = new User();
		UserAvatar avatar = this.getAvatar(userDto);
		UserPrivacySettings privacySettings = this.getPrivacySettings(userDto);
		UserLocation userLocation = this.getLocation(userDto);
		UserContacts contacts = this.getContacts(userDto);
		UserRoles roles = this.getUserRoles(userDto);

		user.setAvatar(avatar);
		user.setPrivacySettings(privacySettings);
		user.setLocation(userLocation);
		user.setContacts(contacts);
		user.setName(userDto.name());
		user.setBirthDate(userDto.birthDate());
		user.setUserRoles(roles);

		return user;
	}

	private UserLocation getLocation(CreateUserInputDto user) {
		UserLocation location = new UserLocation();
		CreateUserLocationInputDto inputDto = user.location();

		location.setCity(inputDto.ciry());
		location.setCountryCode(inputDto.countryCode());

		return location;
	}

	private UserAvatar getAvatar(CreateUserInputDto user) {
		UserAvatar avatar = new UserAvatar();

		avatar.setOptimizedUrl(user.avatarUrl());
		avatar.setUrl(user.avatarUrl());

		return avatar;
	}

	public UserSessionMeta getUserSessionMeta() {
		UserSessionMeta meta = new UserSessionMeta();

		return meta;
	}

	public User getUserPassword(User user, CreateUserInputDto userDto) {
		String password = userDto.password();
		String passwordHash = this.getHashedPassword(password);
		UserTokens tokens = this.getUserTokens(userDto);

		user.setTokens(tokens);
		user.setPasswordHash(passwordHash);

		return user;
	}

	private UserPrivacySettings getPrivacySettings(CreateUserInputDto user) {
		UserPrivacySettings settings = new UserPrivacySettings();
		CreateUserPrivacySettingsInputDto inputDto = user.privacySettings();

		settings.setIsAllowedToSearch(inputDto.isAllowedToSearch());
		settings.setIsHiddenContactInfo(inputDto.isHiddenContactInfo());

		return settings;
	}

	private UserTokens getUserTokens(CreateUserInputDto user) {
		UserTokens tokens = new UserTokens();

		String privateToken = this.getPrivateToken(user);
		String privateKey = this.getPrivateKey(user);

		tokens.setPrivateKey(privateKey);
		tokens.setPrivateToken(privateToken);

		return tokens;
	}

	private UserRoles getUserRoles(CreateUserInputDto updatedUser) {
		UserRoles userRoles = new UserRoles();

		userRoles.setRole(updatedUser.userRole());

		return userRoles;
	}

	private UserContacts getContacts(CreateUserInputDto user) {
		UserContacts contacts = new UserContacts();

		contacts.setEmail(user.name());
		contacts.setPhone(user.contacts().phone());

		return contacts;
	}

	private String getPrivateToken(CreateUserInputDto user) {
		Long userId = this.fakeUserId();
		String currentDateTime = LocalDateTime.now().toString();
		String[] userKeyFields = { user.name(), userId.toString(), currentDateTime };
		String hashParams = String.join(", ", userKeyFields);

		return this.hashService.generateHash(hashParams);
	}

	private String getPrivateKey(CreateUserInputDto user) {
		Long userId = this.fakeUserId();
		String currentDateTime = LocalDateTime.now().toString();
		String[] userKeyFields = { user.name(), userId.toString(), currentDateTime };
		String hashParams = String.join(", ", userKeyFields);

		return this.hashService.generateHash(hashParams);
	}

	private Long fakeUserId() {
		Random random = new Random();
		return random.nextLong(100);
	}

	private String getHashedPassword(String password) {
		return this.hashService.decrypt(password);
	}
}
