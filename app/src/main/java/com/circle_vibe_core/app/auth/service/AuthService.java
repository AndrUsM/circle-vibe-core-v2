package com.circle_vibe_core.app.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.auth.dto.AuthOutputDto;
import com.circle_vibe_core.app.auth.dto.AuthRestorePasswordInputDto;
import com.circle_vibe_core.app.auth.dto.SignInInputDto;
import com.circle_vibe_core.app.user.dto.CreateUserInputDto;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.repository.UserBaseRepository;
import com.circle_vibe_core.app.user.service.UserAuthQueryService;
import com.circle_vibe_core.app.user.service.UserBaseService;
import com.circle_vibe_core.app.user.service.UserCreateService;
import com.circle_vibe_core.app.user.service.UserUpdateService;

@Service
public class AuthService {
	private final UserBaseService userBaseService;
	private final UserBaseRepository userBaseRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserAuthQueryService authQueryService;
	private final UserCreateService userCreateService;
	private final UserUpdateService userUpdateService;

	public AuthService(UserBaseService userBaseService, JwtService jwtService,
			AuthenticationManager authenticationManager, UserAuthQueryService authQueryService,
			UserUpdateService userUpdateService, UserCreateService userCreateService,
			UserBaseRepository userBaseRepository) {
		this.authQueryService = authQueryService;
		this.jwtService = jwtService;
		this.userBaseService = userBaseService;
		this.authenticationManager = authenticationManager;
		this.userCreateService = userCreateService;
		this.userUpdateService = userUpdateService;
		this.userBaseRepository = userBaseRepository;
	}

	public AuthOutputDto signIn(SignInInputDto params) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(params.email(), params.password()));
		User user = this.userBaseService.getByEmail(params.email());

		return this.getAuthJwtToken(user);

	}

	public AuthOutputDto signUp(CreateUserInputDto userInputDto) {
		String email = userInputDto.contacts().email();
		Boolean isExists = this.userBaseService.getByEmail(email) != null;

		if (isExists) {
			return new AuthOutputDto();
		}

		User user = this.userCreateService.getCreatedUser(userInputDto);
		User savedUser = this.userBaseRepository.create(user);
		User userWithUpdatedToken = this.userUpdateService.getpdatedUserTokens(savedUser);
		User finalUser = this.userBaseRepository.update(userWithUpdatedToken);

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userInputDto.contacts().email(), userInputDto.password()));
		return this.getAuthJwtToken(finalUser);
	}

	public AuthOutputDto restorePassword(Long userId, AuthRestorePasswordInputDto inputDto) {
		User user = this.userBaseService.getById(userId);
		String password = inputDto.password();

		if (user == null) {
			return new AuthOutputDto();
		}

		User updatedUserInputDto = this.userUpdateService.getUpdatedUserPassword(user, password);
		this.userBaseRepository.update(updatedUserInputDto);

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getContacts().getEmail(), password));

		return this.getAuthJwtToken(updatedUserInputDto);
	}

	private AuthOutputDto getAuthJwtToken(User user) {
		UserDetails details = this.authQueryService.getByUser(finalUser);
		String token = this.jwtService.generateToken(details);

		return new AuthOutputDto(token);
	}
}
