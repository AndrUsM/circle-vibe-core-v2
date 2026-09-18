package com.circle_vibe_core.app.chatParticipant.service;

import java.util.ArrayList;
import java.util.List;

import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantFilterParams;
import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ChatParticipantPredicateService {
		public List<Predicate> buildFilterPredicates(
			CriteriaBuilder builder,
			Root<ChatParticipant> participant,
			ChatParticipantFilterParams params) {
		List<Predicate> predicates = new ArrayList<>();

		return predicates;
	}
}
