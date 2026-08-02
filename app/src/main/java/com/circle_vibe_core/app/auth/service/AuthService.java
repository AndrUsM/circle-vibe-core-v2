package com.circle_vibe_core.app.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.auth.dto.AuthOutputDto;
import com.circle_vibe_core.app.auth.dto.AuthRestorePasswordInputDto;
import com.circle_vibe_core.app.auth.dto.SignInInputDto;
import com.circle_vibe_core.app.shared.exceptions.UserAlreadyExistsException;
import com.circle_vibe_core.app.user.dto.BasicAuthHashInputDto;
import com.circle_vibe_core.app.user.dto.CreateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.repository.UserBaseRepository;
import com.circle_vibe_core.app.user.service.UserAuthQueryService;
import com.circle_vibe_core.app.user.service.UserBaseService;
import com.circle_vibe_core.app.user.service.UserCreateService;
import com.circle_vibe_core.app.user.service.UserDomainMapService;
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
	private final UserDomainMapService userDomainMapService;

	public AuthService(UserBaseService userBaseService, JwtService jwtService,
			AuthenticationManager authenticationManager, UserAuthQueryService authQueryService,
			UserUpdateService userUpdateService, UserCreateService userCreateService,
			UserBaseRepository userBaseRepository, UserDomainMapService userDomainMapService) {
		this.authQueryService = authQueryService;
		this.jwtService = jwtService;
		this.userBaseService = userBaseService;
		this.authenticationManager = authenticationManager;
		this.userCreateService = userCreateService;
		this.userUpdateService = userUpdateService;
		this.userBaseRepository = userBaseRepository;
		this.userDomainMapService = userDomainMapService;
	}

	public AuthOutputDto signIn(SignInInputDto params) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(params.email(), params.password()));
		User user = this.userBaseService.getByEmail(params.email());

		if (user == null) {
			throw new BadCredentialsException("Incorrect email or password");
		}

		return this.getAuthJwtToken(user);

	}

	public AuthOutputDto signUp(CreateUserInputDto userInputDto) {
		String email = userInputDto.contacts().email();
		Boolean isExists = this.userBaseService.getByEmail(email) != null;

		if (isExists) {
			throw new UserAlreadyExistsException("Account with that email already created");
		}

		User user = this.userCreateService.getCreatedUser(userInputDto);
		User savedUser = this.userBaseRepository.create(user);
		User userWithUpdatedToken = this.userUpdateService.getpdatedUserTokens(savedUser);
		User finalUser = this.userBaseRepository.update(userWithUpdatedToken);

		return this.getAuthJwtToken(finalUser);
	}

	public AuthOutputDto restorePassword(Long userId, AuthRestorePasswordInputDto inputDto) {
		User user = this.userBaseService.getById(userId);
		String password = inputDto.password();
		String privateToken = inputDto.privateToken();

		if (user == null || privateToken == null) {
			throw new BadCredentialsException("Incorrect email or password");
		}

		BasicAuthHashInputDto basicAuthHashParams = new BasicAuthHashInputDto(user.getContacts().getEmail(), privateToken);
		UserDetails userDetails = this.authQueryService.getUserByBasicTokenData(basicAuthHashParams);

		if (userDetails == null) {
			throw new BadCredentialsException("Incorrect email or password");
		}

		User updatedUserInputDto = this.userUpdateService.getUpdatedUserPassword(user, password);
		this.userBaseRepository.update(updatedUserInputDto);

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getContacts().getEmail(), password));

		return this.getAuthJwtToken(updatedUserInputDto);
	}

	private AuthOutputDto getAuthJwtToken(User user) {
		UserDetails details = this.authQueryService.getByUser(user);
		String token = this.jwtService.generateToken(details);
		UserOutputDto userOutputDto = this.userDomainMapService.toUser(user);

		return new AuthOutputDto(token, userOutputDto);
	}
}
