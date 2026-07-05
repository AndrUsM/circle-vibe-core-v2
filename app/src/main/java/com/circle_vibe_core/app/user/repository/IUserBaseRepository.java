package com.circle_vibe_core.app.user.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.circle_vibe_core.app.user.dto.CreateUserInputDto;
import com.circle_vibe_core.app.user.dto.UserOutputDto;
import com.circle_vibe_core.app.user.entity.User;

public interface IUserBaseRepository extends JpaRepository<User, UUID> {
    UserOutputDto create(CreateUserInputDto inputDto);
    boolean delete(Long id);
}
