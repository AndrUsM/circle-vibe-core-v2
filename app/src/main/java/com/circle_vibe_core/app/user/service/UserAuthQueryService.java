package com.circle_vibe_core.app.user.service;

import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.entity.UserContacts;
import com.circle_vibe_core.app.user.repository.UserQueryRepository;

import jakarta.transaction.Transactional;

@Service
public class UserAuthQueryService {
	private final UserQueryRepository userQueryRepository;

	public UserAuthQueryService(UserQueryRepository userQueryRepository) {
		this.userQueryRepository = userQueryRepository;
	}

	@Transactional()
	public UserDetails getByUser(com.circle_vibe_core.app.user.entity.User user) {
		UserContacts userContacts = user.getContacts();

		return User.builder().username(userContacts.getEmail()).password(user.getPasswordHash()).build();
	}
}
