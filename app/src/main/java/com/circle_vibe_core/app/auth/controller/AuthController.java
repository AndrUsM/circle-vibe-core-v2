package com.circle_vibe_core.app.auth.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.circle_vibe_core.app.auth.dto.AuthOutputDto;
import com.circle_vibe_core.app.auth.dto.AuthRestorePasswordInputDto;
import com.circle_vibe_core.app.auth.dto.SignInInputDto;
import com.circle_vibe_core.app.auth.service.AuthService;
import com.circle_vibe_core.app.user.dto.CreateUserInputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Operation(summary = "Sign-in to account")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User logged in"),
		@ApiResponse(responseCode = "400", description = "Wrong credentials")
	})
	@PostMapping("/sign-in")
	public AuthOutputDto signIn(@Valid @RequestBody SignInInputDto inputDto) {
		return this.authService.signIn(inputDto);
	}

	@Operation(summary = "Create new account")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User logged in"),
		@ApiResponse(responseCode = "400", description = "Something wrong with input data")
	})
	@PostMapping("/sign-up")
	public AuthOutputDto signUp(@Valid @RequestBody CreateUserInputDto inputDto) {
		return this.authService.signUp(inputDto);
	}

	@Operation(summary = "Restore password for already created account")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User logged in"),
		@ApiResponse(responseCode = "400", description = "Something wrong with input data")
	})
	@PutMapping("/{id}/restore-password")
	public AuthOutputDto restorePassword(@PathVariable("id") Long userId, @Valid @RequestBody AuthRestorePasswordInputDto inputDto) {
		return this.authService.restorePassword(userId, inputDto);
	}
}
