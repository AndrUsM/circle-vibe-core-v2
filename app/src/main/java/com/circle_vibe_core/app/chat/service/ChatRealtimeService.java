package com.circle_vibe_core.app.chat.service;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.message.controller.MessageService;
import com.circle_vibe_core.app.message.dto.CreateMessageInputDto;
import com.circle_vibe_core.app.message.entity.Message;
import com.circle_vibe_core.app.realtime.dto.RealtimeEvent;
import com.circle_vibe_core.app.realtime.enums.RealtimeEventType;
import com.circle_vibe_core.app.realtime.service.EventPublisherService;
import com.circle_vibe_core.app.shared.service.EncryptionService;

@Service
public class ChatRealtimeService {
	private final MessageService messageService;
	private final EncryptionService encryptionService;
	private final EventPublisherService eventPublisher;

	public ChatRealtimeService(MessageService messageService, EncryptionService encryptionService,
			EventPublisherService eventPublisher) {
		this.messageService = messageService;
		this.encryptionService = encryptionService;
		this.eventPublisher = eventPublisher;
	}

	public Message sendMessage(
			CreateMessageInputDto request,
			Long userId) {

		// String encryptedContent = encryptionService.encrypt(
		// 		request.content);

		Long conversatioId = request.referenceMeta.chatId;

		// Message message = new Message(
		// 		conversatioId,
		// 		userId,
		// 		encryptedContent);


		// Message saved = messageService.save(message);

		Message response = new Message();

		eventPublisher.publishToTopic(
				"/topic/conversations/" + conversatioId,
				RealtimeEvent.of(
						RealtimeEventType.MESSAGE_CREATED,
						response));

		return response;
	}
}
