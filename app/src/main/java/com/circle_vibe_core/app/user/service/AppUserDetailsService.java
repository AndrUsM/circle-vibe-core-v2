package com.circle_vibe_core.app.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.repository.UserQueryRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	private final UserQueryRepository userQueryRepository;

	public AppUserDetailsService(UserQueryRepository userQueryRepository) {
		this.userQueryRepository = userQueryRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserFilterParams params = new UserFilterParams(email, null);
		User user = this.userQueryRepository.getByFilter(params);


		    System.out.println("AUTH USER FOUND = " + (user != null));


		if (user == null) {
			String message = "User with email: %s not found!".formatted(email);
			throw new UsernameNotFoundException(message);
		}

		return org.springframework.security.core.userdetails.User
				.withUsername(user.getContacts().getEmail())
				.password(user.getPasswordHash())
				.authorities(user.getUserRoles().getRole().toString())
				.build();
	}
}
