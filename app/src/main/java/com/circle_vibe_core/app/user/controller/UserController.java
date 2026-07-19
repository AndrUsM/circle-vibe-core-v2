package com.circle_vibe_core.app.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.service.UserBaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserBaseService userBaseService;

	public UserController(UserBaseService userBaseService) {
		this.userBaseService = userBaseService;
	}

	@Operation(summary = "Get paginated users")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "List of paginated users"),
		@ApiResponse(responseCode = "500", description = "Server exception")
	})
	@GetMapping("/users")
	public Page<UserOutputDto> getUsersPaginated(@RequestParam PaginationParamsDto pagination, @RequestParam UserFilterParams filters) {
		return this.userBaseService.getAllPaginated(pagination, filters);
	}
}
