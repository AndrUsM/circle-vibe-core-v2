package com.circle_vibe_core.app.chat.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.chat.dto.ChatInputDto;
import com.circle_vibe_core.app.chat.dto.ChatSettingsInputDto;
import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.entity.ChatMeta;
import com.circle_vibe_core.app.chat.entity.ChatSecurity;
import com.circle_vibe_core.app.chat.entity.ChatSettings;
import com.circle_vibe_core.app.chat.enums.ChatType;
import com.circle_vibe_core.app.shared.service.HashService;

@Service
public class ChatEntityModificationMapService {
	private final HashService hashService;

	public ChatEntityModificationMapService(HashService hashService) {
		this.hashService = hashService;
	}

	public Chat toChatCreateDto(ChatInputDto inputDto) {
		ChatSettings chatSettings = this.getChatSettings(inputDto.chatSettings);
		ChatMeta meta = this.getChatMeta(inputDto);
		ChatSecurity chatSecurity = this.getChatSecurity(inputDto);

		Chat chat = this.setBaseFields(new Chat(), inputDto);
		chat.setChatSettings(chatSettings);
		chat.setChatMeta(meta);
		chat.setChatSecurity(chatSecurity);

		return chat;
	}

	public Chat toChatUpdateDto(Long chatId, Chat currentEntity, ChatInputDto inputDto) {
		ChatSettings chatSettings = this.getChatSettings(inputDto.chatSettings);
		ChatMeta meta = this.getChatMeta(inputDto);

		Chat chat = this.setBaseFields(new Chat(), inputDto);
		chat.setId(chatId);
		chat.setChatSettings(chatSettings);
		chat.setChatMeta(meta);
		chat.setChatSecurity(currentEntity.getChatSecurity());

		return chat;
	}

	private Chat setBaseFields(Chat chat, ChatInputDto inputDto) {
		chat.setAvatarUrl(inputDto.avatarUrl);
		chat.setUpdatedAt(LocalDateTime.now());

		return chat;
	}

	private ChatMeta getChatMeta(ChatInputDto inputDto) {
		ChatMeta meta = new ChatMeta();

		meta.setBlockReason(null);
		meta.setDescription(inputDto.description);
		meta.setName(inputDto.name);
		meta.setIsActive(true);
		meta.setIsRemoved(false);

		return meta;
	}

	private ChatSettings getChatSettings(ChatSettingsInputDto inputDto) {
		ChatSettings chatSettings = new ChatSettings();

		chatSettings.setChatType(inputDto.chatType != null ? inputDto.chatType : ChatType.PRIVATE);
		chatSettings.setIsGroupChat(inputDto.isGroupChat);
		chatSettings.setUsersLimit(inputDto.usersLimit != null ? inputDto.usersLimit : 100);

		return chatSettings;
	}

	private ChatSecurity getChatSecurity(ChatInputDto inputDto) {
		ChatSecurity chatSecurity = new ChatSecurity();

		String hashParams = this.composeHasParams(inputDto);
		String encryptionSecret = this.hashService.generateHash(hashParams);
		
		chatSecurity.setEncryptionSecret(encryptionSecret);
		return chatSecurity;
	}

	private String composeHasParams(ChatInputDto inputDto) {
		return String.format(
				"%s-%s-%s",
				inputDto.name,
				inputDto.chatSettings.chatType,
				UUID.randomUUID().toString());
	}
}