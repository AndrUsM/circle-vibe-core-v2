package com.circle_vibe_core.app.shared.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Order;

public class PageableFactory {

	private static final int DEFAULT_PAGE = 0;
	private static final int DEFAULT_SIZE = 20;
	private static final int MAX_SIZE = 100;

	public Pageable create(
			Integer page,
			Integer size,
			String sortBy,
			Sort.Direction direction) {

		return PageRequest.of(
				page == null || page < 0 ? DEFAULT_PAGE : page,
				Math.min(size == null ? DEFAULT_SIZE : size, MAX_SIZE),
				Sort.by(
						direction == null ? Sort.Direction.ASC : direction,
						sortBy == null || sortBy.isBlank() ? "id" : sortBy));
	}

	public <T> CriteriaQuery<T> applyQuery(
			CriteriaQuery<T> query,
			Pageable pageable,
			CriteriaBuilder builder,
			Root<T> root) {

		if (pageable.getSort().isSorted()) {

			List<Order> orders = pageable.getSort()
					.stream()
					.map(sort -> sort.isAscending()
							? builder.asc(root.get(sort.getProperty()))
							: builder.desc(root.get(sort.getProperty())))
					.toList();

			query.orderBy(orders);
		}

		return query;
	}

	public <T> Page<T> buildPage(
            List<T> content,
            Pageable pageable,
            long total) {

        return new PageImpl<>(
                content,
                pageable,
                total
        );
    }

	public <T> TypedQuery<T> buildTypedPaginationQuery(
		EntityManager entityManager,
        CriteriaQuery<T> query,
        Pageable pageable) {

    TypedQuery<T> typedQuery = entityManager.createQuery(query);

    typedQuery.setMaxResults(pageable.getPageSize());
    typedQuery.setFirstResult((int) pageable.getOffset());

    return typedQuery;
}
}