package com.circle_vibe_core.app.chatParticipant.service;

import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantCreateInputDto;
import com.circle_vibe_core.app.chatParticipant.dto.ChatParticipantUpdateInputDto;
import com.circle_vibe_core.app.chatParticipant.entity.ChatParticipant;
import com.circle_vibe_core.app.chatParticipant.repository.ChatParticipantBaseRepository;
import com.circle_vibe_core.app.chatParticipant.repository.ChatParticipantQueryRepository;
import com.circle_vibe_core.app.chatParticipant.repository.ChatParticipantRepository;

import jakarta.transaction.Transactional;

public class ChatParticipantCreateService {
	private final ChatParticipantRepository chatParticipantRepository;
	private final ChatParticipantBaseRepository chatParticipantBaseRepository;
	private final ChatParticipantQueryRepository chatParticipantQueryRepository;
	private final ChatParticipantCreateUpdatePayloadService chatParticipantCreateUpdatePayloadService;

	public ChatParticipantCreateService(ChatParticipantBaseRepository chatParticipantBaseRepository,
			ChatParticipantCreateUpdatePayloadService chatParticipantCreateUpdatePayloadService,
			ChatParticipantQueryRepository chatParticipantQueryRepository,
			ChatParticipantRepository chatParticipantRepository) {
		this.chatParticipantBaseRepository = chatParticipantBaseRepository;
		this.chatParticipantCreateUpdatePayloadService = chatParticipantCreateUpdatePayloadService;
		this.chatParticipantQueryRepository = chatParticipantQueryRepository;
		this.chatParticipantRepository = chatParticipantRepository;
	}

	@Transactional()
	public ChatParticipant create(ChatParticipantCreateInputDto inputDto) {
		return this.chatParticipantBaseRepository
				.create(this.chatParticipantCreateUpdatePayloadService.toCreate(inputDto));
	}

	@Transactional()
	public ChatParticipant update(Long id, ChatParticipantUpdateInputDto inputDto) {
		return this.chatParticipantCreateUpdatePayloadService.toUpdate(id, inputDto);
	}

	@Transactional()
	public int delete(Long id) {
		return this.chatParticipantBaseRepository.deleteById(id);
	}

	@Transactional()
	public ChatParticipant get(Long id) {
		return this.chatParticipantRepository.getReferenceById(id);
	}
}
