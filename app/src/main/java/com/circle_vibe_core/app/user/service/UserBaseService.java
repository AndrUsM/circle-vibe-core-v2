package com.circle_vibe_core.app.user.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.user.dto.UpdateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.repository.UserBaseRepository;
import com.circle_vibe_core.app.user.repository.UserQueryRepository;

import jakarta.transaction.Transactional;

@Repository
public class UserBaseService {
  private final UserQueryRepository userQueryRepository;
  private final UserBaseRepository userBaseRepository;
  private final UserUpdateService userUpdateService;

  public UserBaseService(UserQueryRepository userQueryRepository, UserBaseRepository userBaseRepository, UserUpdateService userUpdateService) {
    this.userBaseRepository = userBaseRepository;
    this.userQueryRepository = userQueryRepository;
    this.userUpdateService = userUpdateService;
  }

  @Transactional()
  public List<User> getAll(UserFilterParams params) {
    return this.userBaseRepository.getAll(params);
  }

  @Transactional()
  public User getById(Long id) {
    return this.userQueryRepository.getById(id);
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
