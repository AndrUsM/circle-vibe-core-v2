package com.circle_vibe_core.app.user.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.user.dto.UpdateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.repository.UserBaseRepository;
import com.circle_vibe_core.app.user.repository.UserQueryRepository;

import jakarta.transaction.Transactional;

@Repository
public class UserBaseService {
	private final UserQueryRepository userQueryRepository;
	private final UserBaseRepository userBaseRepository;
	private final UserUpdateService userUpdateService;
	private final UserDomainMapService userDomainMapService;

	public UserBaseService(UserQueryRepository userQueryRepository, UserBaseRepository userBaseRepository,
			UserUpdateService userUpdateService, UserDomainMapService userDomainMapService) {
		this.userBaseRepository = userBaseRepository;
		this.userQueryRepository = userQueryRepository;
		this.userUpdateService = userUpdateService;
		this.userDomainMapService = userDomainMapService;
	}

	@Transactional()
	public Page<UserOutputDto> getAllPaginated(PaginationParamsDto paginationDto, UserFilterParams params) {
		Page<User> paginatedUsers = this.userQueryRepository.getAllPaginated(paginationDto, params);

		return paginatedUsers.map(user -> this.userDomainMapService.toUser(user));
	}

	@Transactional()
	public User getById(Long id) {
		return this.userQueryRepository.getById(id);
	}

	@Transactional()
	public User getByEmail(String email) {
		UserFilterParams filters = new UserFilterParams(email, null);
		return this.userQueryRepository.getByFilter(filters);
	}



	public User update(Long id, UpdateUserInputDto payload) {
		User user = getById(id);
		User updatedUser = this.userUpdateService.getUpdatedUser(user, payload);

		return this.userBaseRepository.update(updatedUser);
	}

	private User updatePassword(Long id, String password) {
		User user = getById(id);
		User updatedUser = this.userUpdateService.getUpdatedUserPassword(user, password);

		return this.userBaseRepository.update(updatedUser);
	}

	private User updateUserTokens(Long id) {
		User user = getById(id);
		User updatedUser = this.userUpdateService.getUserWithUpdatedTokens(user);

		return this.userBaseRepository.update(updatedUser);
	}
}
