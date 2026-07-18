package com.circle_vibe_core.app.user.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.shared.service.HashService;
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
public class UserUpdateService {
  private final HashService hashService;

  public UserUpdateService(HashService hashService) {
    this.hashService = hashService;
  }

  public User getUpdatedUser(User user, UpdateUserInputDto updatedUser) {
    UserAvatar avatar = this.getAvatar(user, updatedUser);
    UserPrivacySettings privacySettings = this.getPrivacySettings(user, updatedUser);
    UserLocation userLocation = this.getLocation(user, updatedUser);
    UserContacts contacts = this.getContacts(user, updatedUser);
	UserRoles roles = this.getUserRoles(user, updatedUser);

    user.setAvatar(avatar);
    user.setPrivacySettings(privacySettings);
    user.setLocation(userLocation);
    user.setContacts(contacts);
    user.setName(updatedUser.name());
    user.setBirthDate(updatedUser.birthDate());
	user.setUserRoles(roles);

    return user;
  }

  public User getUserWithUpdatedTokens(User user) {
    UserTokens tokens = this.getUpdatedUserTokens(user);

    user.setTokens(tokens);

    return user;
  }

  public UserTokens getUpdatedUserTokens(User user) {
    UserTokens tokens = new UserTokens();

    String privateToken = this.getPrivateToken(user);
    String privateKey = this.getPrivateKey(user);
    
    tokens.setPrivateKey(privateKey);
    tokens.setPrivateToken(privateToken);

    return tokens;
  }

  public User getpdatedUserTokens(User user) {
	UserTokens tokens = this.getUpdatedUserTokens(user);

	user.setTokens(tokens);

	return user;
  }

  public User getUpdatedUserPassword(User user, String password) {
    String passwordHash = this.getHashedPassword(password);
    UserTokens tokens = this.getUpdatedUserTokens(user);

    user.setTokens(tokens);
    user.setPasswordHash(passwordHash);

    return user;
  }

  public UserSessionMeta getUserSessionMeta() {
    UserSessionMeta meta = new UserSessionMeta();

    return meta;
  }

  private UserAvatar getAvatar(User user, UpdateUserInputDto updatedUser) {
    UserAvatar avatar = new UserAvatar();

    avatar.setOptimizedUrl(updatedUser.avatarUrl());
    avatar.setUrl(updatedUser.avatarUrl());
    
    return avatar;
  }

  private UserPrivacySettings getPrivacySettings(User user, UpdateUserInputDto updatedUser) {
    UserPrivacySettings settings = new UserPrivacySettings();
    
    settings.setIsAllowedToSearch(updatedUser.isAllowedToSearch());
    settings.setIsHiddenContactInfo(updatedUser.isHiddenContactInfo());

    return settings;
  }

  private UserLocation getLocation(User user, UpdateUserInputDto updatedUser) {
    UserLocation location = new UserLocation();
    
    location.setCity(updatedUser.city());
    location.setCountryCode(updatedUser.countryCode());

    return location;
  }

  private UserContacts getContacts(User user, UpdateUserInputDto updatedUser) {
    UserContacts contacts = new UserContacts();

    contacts.setEmail(updatedUser.email());
    contacts.setPhone(updatedUser.phone());

    return contacts;
  }

  private String getPrivateKey(User user) {
    Long userId = user.getId();
    String currentDateTime = LocalDateTime.now().toString();
    String[] userKeyFields = {user.getName(), userId.toString(), currentDateTime};
    String hashParams = String.join(", ", userKeyFields);

    return this.hashService.generateHash(hashParams);
  }

  private String getPrivateToken(User user) {
    Long userId = user.getId();
    String currentDateTime = LocalDateTime.now().toString();
    String[] userKeyFields = {user.getName(), userId.toString(), currentDateTime};
    String hashParams = String.join(", ", userKeyFields);

    return this.hashService.generateHash(hashParams);
  }

  private String getHashedPassword(String password) {
    return this.hashService.decrypt(password);
  }

  private UserRoles getUserRoles(User user, UpdateUserInputDto updatedUser) {
	UserRoles userRoles = new UserRoles();

	userRoles.setRole(updatedUser.userRole());

	return userRoles;
  }
}
