package com.circle_vibe_core.app.user.helpers;

import java.util.ArrayList;
import java.util.List;

import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FilterPredicatesService {
	public List<Predicate> buildUserFilterPredicates(
			CriteriaBuilder builder,
			Root<User> user,
			UserFilterParams params) {
		List<Predicate> predicates = new ArrayList<>();

		if (params.email != null && !params.email.isBlank()) {
			predicates.add(
					builder.equal(user.get("email"), params.email));
		}

		return predicates;
	}
}
