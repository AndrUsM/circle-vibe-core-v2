package com.circle_vibe_core.app.message.repository;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.message.entity.Message;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class MessageBaseRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public MessageBaseRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public Message create(Message message) {
		this.entityManager.persist(message);

		return message;
	}

	@Transactional
	public int deleteById(Long id) {
		return this.entityManager.createQuery("""
					DELETE FROM Message m WHERE m.id = :id
				""").setParameter("id", id).executeUpdate();
	}

	@Transactional
	public int deleteRelatedFiles(Long messageId) {
		return this.entityManager.createQuery("""
					DELETE FROM MessageFile mf WHERE mf.messageId = :messageId
				""").setParameter("messageId", messageId).executeUpdate();
	}

	@Transactional
	public Message update(Message message) {
		return this.entityManager.merge(message);
	}
}
