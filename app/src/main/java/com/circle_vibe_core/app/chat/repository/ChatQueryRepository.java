package com.circle_vibe_core.app.chat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.helpers.ChatFilterPredicatesService;
import com.circle_vibe_core.app.chat.params.ChatFilterParams;
import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.shared.service.PageableFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

@Repository 
public class ChatQueryRepository {
	@PersistenceContext
	private EntityManager entityManager;
	private final PageableFactory pageableFactory = new PageableFactory();
	private final ChatFilterPredicatesService filterPredicates = new ChatFilterPredicatesService();

	public Chat getById(Long id) {
		return this.entityManager.find(Chat.class, id);
	}

	public Chat getByFilter(ChatFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> query = builder.createQuery(Chat.class);
		Root<Chat> chat = query.from(Chat.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, chat, params);

		query.select(chat);

		if (!predicates.isEmpty()) {
			query.where(predicates);
		}

		return entityManager
			.createQuery(query)
			.getResultStream()
			.findFirst()
			.orElse(null);
	}

	public List<Chat> getAll(ChatFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> query = builder.createQuery(Chat.class);
		Root<Chat> chat = query.from(Chat.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, chat, params);

		if (!predicates.isEmpty()) {
			query.where(predicates);

			return entityManager.createQuery(query).getResultList();
		}

		return entityManager.createQuery(query).getResultList();
	}

	public Long count(ChatFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Chat> chat = query.from(Chat.class);

		query.select(builder.count(chat));

		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, chat, params);

		if (predicates.isEmpty()) {
			return entityManager
				.createQuery(query)
				.getSingleResult();
		}

		query.where(predicates);

		return entityManager.createQuery(query).getSingleResult();
	}

	public Page<Chat> getAllPaginated(PaginationParamsDto paginationDto, ChatFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> query = builder.createQuery(Chat.class);
		Root<Chat> chat = query.from(Chat.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, chat, params);
		Long total = this.count(params);
		Pageable pageable = this.pageableFactory.create(
			paginationDto.page(),
			paginationDto.size(),
			paginationDto.sortBy(),
			paginationDto.direction()
		);

		if (!predicates.isEmpty()) {
			query.where(predicates);

			TypedQuery<Chat> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager, query, pageable);
			List<Chat> content = typedQuery.getResultList();

			return this.pageableFactory.buildPage(content, pageable, total);
		}

		query = this.pageableFactory.applyQuery(query, pageable, builder, chat);

		TypedQuery<Chat> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager, query, pageable);
		List<Chat> content = typedQuery.getResultList();

		return this.pageableFactory.buildPage(content, pageable, total);
	}
}
