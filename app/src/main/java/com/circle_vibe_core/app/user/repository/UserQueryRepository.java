package com.circle_vibe_core.app.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.shared.service.PageableFactory;
import com.circle_vibe_core.app.shared.service.QueryLoader;
import com.circle_vibe_core.app.user.dto.UserByTokenParams;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;
import com.circle_vibe_core.app.user.helpers.FilterPredicatesService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserQueryRepository {
	@PersistenceContext
	private EntityManager entityManager;

	private final QueryLoader queryLoader = new QueryLoader();
	private final PageableFactory pageableFactory = new PageableFactory();
	private final FilterPredicatesService filterPredicates = new FilterPredicatesService();

	public User getByToken(UserByTokenParams params) {
		String jpql = queryLoader.load(
				"jpql/user/find-users.jpql");

		return entityManager.createQuery(jpql, User.class)
				.setParameter("onlyActive", params.active())
				.setParameter("token", params.token())
				.getSingleResult();
	}

	public User getById(Long id) {
		return this.entityManager.find(User.class, id);
	}

	public User getByFilter(UserFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> user = query.from(User.class);
		List<Predicate> predicates = this.filterPredicates.buildUserFilterPredicates(builder, user, params);
		;

		query.select(user);

		if (!predicates.isEmpty()) {
			query.where(predicates);
		}

		return entityManager.createQuery(query).getSingleResult();
	}

	public boolean existsByFilter(UserFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<User> user = query.from(User.class);
		List<Predicate> predicates = this.filterPredicates.buildUserFilterPredicates(builder, user, params);

		query.select(builder.count(user)).where(predicates);

		return entityManager.createQuery(query).getSingleResult() > 0;
	}

	public List<User> getAll(UserFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> user = query.from(User.class);
		List<Predicate> predicates = this.filterPredicates.buildUserFilterPredicates(builder, user, params);

		if (!predicates.isEmpty()) {
			query.where(predicates);

			return entityManager.createQuery(query).getResultList();
		}

		return entityManager.createQuery(query).getResultList();
	}

	public Long count(UserFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<User> user = query.from(User.class);

		query.select(builder.count(user));

		List<Predicate> predicates = this.filterPredicates.buildUserFilterPredicates(builder, user, params);

		if (predicates.isEmpty()) {
			return entityManager
					.createQuery(query)
					.getSingleResult();
		}

		query.where(predicates);

		return entityManager.createQuery(query).getSingleResult();
	}

	public Page<User> getAllPaginated(PaginationParamsDto paginationDto, UserFilterParams params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> user = query.from(User.class);
		List<Predicate> predicates = this.filterPredicates.buildUserFilterPredicates(builder, user, params);
		Long total = this.count(params);
		Pageable pageable = this.pageableFactory.create(
				paginationDto.page(),
				paginationDto.size(),
				paginationDto.sortBy(),
				paginationDto.direction());

		if (!predicates.isEmpty()) {
			query.where(predicates);

			TypedQuery<User> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager, query, pageable);
			List<User> content = typedQuery.getResultList();

			return this.pageableFactory.buildPage(content, pageable, total);
		}

		query = this.pageableFactory.applyQuery(query, pageable, builder, user);

		TypedQuery<User> typedQuery = this.pageableFactory.buildTypedPaginationQuery(entityManager, query, pageable);
		List<User> content = typedQuery.getResultList();

		return this.pageableFactory.buildPage(content, pageable, total);
	}
}
