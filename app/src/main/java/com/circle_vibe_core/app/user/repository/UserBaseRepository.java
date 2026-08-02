package com.circle_vibe_core.app.user.repository;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserBaseRepository {
	private final EntityManager entityManager;

	public UserBaseRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public User create(User user) {
		this.entityManager.persist(user);
		return user;
	}

	@Transactional
	public int deleteById(Long id) {
		return this.entityManager.createQuery("""
				DELETE FROM User u
				WHERE u.id = :id
				""").setParameter("id", id).executeUpdate();
	}

	@Transactional
	public User update(User user) {
		return this.entityManager.merge(user);
	}
}
