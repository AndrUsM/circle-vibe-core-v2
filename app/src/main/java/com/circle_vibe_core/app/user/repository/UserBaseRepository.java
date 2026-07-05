package com.circle_vibe_core.app.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserBaseRepository {
    private final EntityManager entityManager;

    public UserBaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User create(User user) {
        this.entityManager.persist(user);
        return user;
    }

    public int deleteById(Long id) {
        return this.entityManager.createQuery("""
                DELETE FROM User u
                WHERE u.id = :id
                """).setParameter("id", id).executeUpdate();
    }

    public User update(User user) {
        return this.entityManager.merge(user);
    }

    public List<User> getAll(UserFilterParams params) {
        return this.entityManager.createQuery("""
                SELECT u FROM User u ORDER BY u.id DESC
            """).getResultList();
    }
}
