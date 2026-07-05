package com.circle_vibe_core.app.user.service;

import java.util.List;

import com.circle_vibe_core.app.user.dto.CreateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.entity.User;

public interface IUserBaseService {
    User create(CreateUserInputDto request);

    User update(UUID id, UpdateUserRequest request);

    void delete(UUID id);

    User getById(UUID id);

    List<User> getAll();
}
