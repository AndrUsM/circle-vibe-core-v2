package com.circle_vibe_core.app.chat.helpers;

import java.util.ArrayList;
import java.util.List;

import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.params.ChatFilterParams;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ChatFilterPredicatesService {
	public List<Predicate> buildFilterPredicates(CriteriaBuilder builder, Root<Chat> chat, ChatFilterParams params) {
		List<Predicate> predicates = new ArrayList<>();

		if (params.getIsGroupChat() != null) {
			predicates.add(
				builder.equal(chat.get("chatSettings").get("isGroupChat"), params.isGroupChat.booleanValue())
			);
		}

		if (params.getName() != null) {
			predicates.add(
				builder.in(chat.get("chatMeta").get("name").in(params.getName()))
			);
		}

		if (params.getDescription() != null) {
			predicates.add(
				builder.in(chat.get("chatMeta").get("description").in(params.getDescription()))
			);
		}

		if (params.getChatType() != null) {
			predicates.add(
				builder.equal(chat.get("chatSettings").get("chatType"), params.getChatType())
			);
		}

		return predicates;
	}
}
