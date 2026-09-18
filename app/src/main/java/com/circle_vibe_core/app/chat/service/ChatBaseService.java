package com.circle_vibe_core.app.chat.service;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.chat.dto.ChatInputDto;
import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.repository.ChatBaseRepository;

@Service 
public class ChatBaseService {
	private final ChatBaseRepository chatBaseRepository;
	private final ChatEntityModificationMapService chatDomainMapService;

	public ChatBaseService(ChatBaseRepository chatBaseRepository, ChatEntityModificationMapService chatDomainMapService) {
		this.chatBaseRepository = chatBaseRepository;
		this.chatDomainMapService = chatDomainMapService;
	}

	public Chat create(ChatInputDto inputDto) {
		Chat chat = this.chatDomainMapService.toChatCreateDto(inputDto);
		this.chatBaseRepository.create(chat);
		return chat;
	}

	public Chat update(Long chatId, Chat currentEntity, ChatInputDto inputDto) {
		Chat chat = this.chatDomainMapService.toChatUpdateDto(chatId, currentEntity, inputDto);
	
		return this.chatBaseRepository.update(chat);
	}

	public int deteteById(Long id) {
		return this.chatBaseRepository.deletebyId(id);
	}
}
