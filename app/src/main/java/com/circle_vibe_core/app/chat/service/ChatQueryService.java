package com.circle_vibe_core.app.chat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.params.ChatFilterParams;
import com.circle_vibe_core.app.chat.repository.ChatQueryRepository;
import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;

@Service 
public class ChatQueryService {
	private final ChatQueryRepository chatQueryRepository;

	public ChatQueryService(ChatQueryRepository chatQueryRepository) {
		this.chatQueryRepository = chatQueryRepository;
	}

	public Chat getById(Long id) {
		return this.chatQueryRepository.getById(id);
	}

	public List<Chat> getAll(ChatFilterParams params) {
		return this.chatQueryRepository.getAll(params);
	}

	public Page<Chat> getAllPaginated(PaginationParamsDto paginationDto, ChatFilterParams params) {
		return this.chatQueryRepository.getAllPaginated(paginationDto, params);
	}
}
