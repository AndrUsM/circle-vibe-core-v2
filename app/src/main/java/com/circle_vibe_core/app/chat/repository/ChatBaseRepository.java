package com.circle_vibe_core.app.chat.repository;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.chat.entity.Chat;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository 
public class ChatBaseRepository {
	private final EntityManager entityManager;

	public ChatBaseRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public Chat create(Chat chat) {
		this.entityManager.persist(chat);

		return chat;
	}

	@Transactional 
	public int deletebyId(Long id) {
		return this.entityManager.createQuery("""
			DELETE FROM Chat ch
			WHERE ch.id = :id
		""").setParameter("id", id).executeUpdate();
	}

	@Transactional
	public Chat update(Chat chat) {
		return this.entityManager.merge(chat);
	}
}
