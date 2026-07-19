package com.circle_vibe_core.app.user.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.entity.UserContacts;

import jakarta.transaction.Transactional;

@Service
public class UserAuthQueryService {
	@Transactional()
	public UserDetails getByUser(com.circle_vibe_core.app.user.entity.User user) {
		UserContacts userContacts = user.getContacts();

		return User.builder().username(userContacts.getEmail()).password(user.getPasswordHash()).build();
	}
}
