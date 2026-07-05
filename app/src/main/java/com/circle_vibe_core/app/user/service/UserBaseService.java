package com.circle_vibe_core.app.user.service;

import java.util.List;
import java.util.function.Consumer;

import com.circle_vibe_core.app.user.dto.UpdateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.entity.UserAvatar;
import com.circle_vibe_core.app.user.repository.UserQueryRepository;

import jakarta.transaction.Transactional;

public class UserBaseService {
  private final UserQueryRepository userQueryRepository;
  private final UserBaseService userBaseRepository;

  public UserBaseService(UserQueryRepository userQueryRepository, UserBaseService userBaseRepository) {
    this.userBaseRepository = userBaseRepository;
    this.userQueryRepository = userQueryRepository;
  }

  @Transactional()
  public List<User> getAll(UserFilterParams params) {
    return this.userBaseRepository.getAll(params);
  }

  @Transactional()
  public User getById(Long id) {
    return this.userQueryRepository.getById(id);
  }

  public User update(Long id, UpdateUserInputDto updatedUser) {
    User user = getById(id);

    user.setAvatar(new UserAvatar());

    return user;
  }

  private User

  private void updateIfPresent(Object value, Consumer<Object> setter) {
    if (value != null) {
        setter.accept(value);
    }
  }

  private void updateBaseUser(User)
}
