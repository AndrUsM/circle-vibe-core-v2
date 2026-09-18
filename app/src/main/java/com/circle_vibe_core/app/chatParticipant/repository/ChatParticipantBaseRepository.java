package com.circle_vibe_core.app.chatParticipant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChatParticipantBaseRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public ChatParticipantBaseRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public ChatParticipant create(ChatParticipant participant) {
		this.entityManager.persist(participant);

		return participant;
	}

	@Transactional
	public int deleteById(Long id) {
		return this.entityManager.createQuery("""
					DELETE FROM ChatParticipant chp WHERE chp.id = :id
				""").setParameter("id", id).executeUpdate();
	}

	@Transactional
	public ChatParticipant update(ChatParticipant participant) {
		return this.entityManager.merge(participant);
	}
}
