package com.circle_vibe_core.app.chatParticipant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantFilterParams;
import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;
import com.circle_vibe_core.app.chatParticipant.service.ChatParticipantPredicateService;
import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.shared.service.PageableFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ChatParticipantQueryRepository {
	@PersistenceContext
	private EntityManager entityManager;

	private final PageableFactory pageableFactory = new PageableFactory();
	private final ChatParticipantPredicateService filterPredicates = new ChatParticipantPredicateService();

	public ChatParticipantQueryRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Long count(ChatParticipantFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ChatParticipant> participant = query.from(ChatParticipant.class);

		query.select(builder.count(participant));

		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, participant, params);

		if (predicates.isEmpty()) {
			return entityManager
					.createQuery(query)
					.getSingleResult();
		}

		query.where(predicates);

		return entityManager.createQuery(query).getSingleResult();
	}

	public List<ChatParticipant> getAll(ChatParticipantFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ChatParticipant> query = builder.createQuery(ChatParticipant.class);
		Root<ChatParticipant> participant = query.from(ChatParticipant.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, participant, params);

		if (!predicates.isEmpty()) {
			query.where(predicates);

			return entityManager.createQuery(query).getResultList();
		}

		return entityManager.createQuery(query).getResultList();
	}

	public Page<ChatParticipant> getAllPaginated(PaginationParamsDto paginationDto,
			ChatParticipantFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ChatParticipant> query = builder.createQuery(ChatParticipant.class);
		Root<ChatParticipant> participant = query.from(ChatParticipant.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, participant, params);
		Long total = this.count(params);

		Pageable pageable = this.pageableFactory.create(
				paginationDto.page(),
				paginationDto.size(),
				paginationDto.sortBy(),
				paginationDto.direction());

		if (!predicates.isEmpty()) {
			query.where(predicates);

			TypedQuery<ChatParticipant> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager,
					query, pageable);
			List<ChatParticipant> content = typedQuery.getResultList();

			return this.pageableFactory.buildPage(content, pageable, total);
		}

		query = this.pageableFactory.applyQuery(query, pageable, builder, participant);
		TypedQuery<ChatParticipant> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager, query,
				pageable);
		List<ChatParticipant> content = typedQuery.getResultList();

		return this.pageableFactory.buildPage(content, pageable, total);
	}

	public ChatParticipant getByFilter(ChatParticipantFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ChatParticipant> query = builder.createQuery(ChatParticipant.class);
		Root<ChatParticipant> participant = query.from(ChatParticipant.class);
		List<Predicate> predicates = this.filterPredicates.buildFilterPredicates(builder, participant, params);

		if (!predicates.isEmpty()) {
			query.where(predicates);

			return this.entityManager.createQuery(query).getSingleResultOrNull();
		}

		return this.entityManager.createQuery(query).getSingleResultOrNull();
	}
}
