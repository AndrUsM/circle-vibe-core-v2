package com.circle_vibe_core.app.user.helpers;

import java.util.ArrayList;
import java.util.List;

import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserFilterPredicatesService {
	public List<Predicate> buildFilterPredicates(
			CriteriaBuilder builder,
			Root<User> user,
			UserFilterParams params) {
		List<Predicate> predicates = new ArrayList<>();

		if (params.getEmail() != null && !params.getEmail().isBlank()) {
			predicates.add(
					builder.equal(user.get("contacts").get("email"), params.getEmail()));
		}

		if (params.getPrivateToken() != null && !params.getPrivateToken().isBlank()) {
			predicates.add(
					builder.equal(user.get("tokens").get("privateToken"), params.getPrivateToken()));
		}

		return predicates;
	}
}
