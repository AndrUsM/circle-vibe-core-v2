package com.circle_vibe_core.app.realtime.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.circle_vibe_core.app.realtime.dto.RealtimeEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventPublisherService {
	private final SimpMessagingTemplate messagingTemplate;

	public void publishToUser(
			String username,
			RealtimeEvent<?> event) {
		messagingTemplate.convertAndSendToUser(
				username,
				"/queue/events",
				event);
	}

	public void publishToTopic(
			String destination,
			RealtimeEvent<?> event) {
		messagingTemplate.convertAndSend(
				destination,
				event);
	}
}
