package com.circle_vibe_core.app.chat.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.chat.dto.ChatMetaOutputDto;
import com.circle_vibe_core.app.chat.dto.ChatOutputDto;
import com.circle_vibe_core.app.chat.dto.ChatSecurityOutputDto;
import com.circle_vibe_core.app.chat.dto.ChatSettingsOutputDto;
import com.circle_vibe_core.app.chat.dto.ChatShortOutputDto;
import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.entity.ChatMeta;
import com.circle_vibe_core.app.chat.entity.ChatSecurity;
import com.circle_vibe_core.app.chat.entity.ChatSettings;
import com.circle_vibe_core.app.chat.enums.ChatType;

@Service 
public class ChatDomainMapService {
	private final int usersLimitFallback = 100;

	public ChatOutputDto toChat(Chat chat) {
		ChatOutputDto outputDto = new ChatOutputDto();
		outputDto = this.setBaseFields(chat, outputDto);
		outputDto.setChatMeta(this.getChatMeta(chat));
		outputDto.setChatSettings(this.getChatSettings(chat));
		outputDto.setChatSecurity(this.getChatSecurity(chat));

		return outputDto;
	}

	public ChatShortOutputDto toChatShort(Chat chat) {
		ChatShortOutputDto outputDto = new ChatShortOutputDto();

		outputDto.setId(chat.getId());
		outputDto.setAvatarUrl(Optional.ofNullable(chat.getAvatarUrl()).orElse(null));
		outputDto.setMetaOutputDto(this.getChatMeta(chat));

		return outputDto;
	} 

	private ChatOutputDto setBaseFields(Chat chat, ChatOutputDto outputDto) {
		outputDto.setId(chat.getId());
		outputDto.setAvatarUrl(Optional.ofNullable(chat.getAvatarUrl()).orElse(null));

		return outputDto;
	}

	private ChatMetaOutputDto getChatMeta(Chat chat) {
		ChatMetaOutputDto metaOutput = new ChatMetaOutputDto();
		ChatMeta meta = chat.getChatMeta();

		metaOutput.setIsActive(Optional.ofNullable(meta.getIsActive()).orElse(true));
		metaOutput.setBlockReason(Optional.ofNullable(meta.getBlockReason()).orElse(null));
		metaOutput.setIsRemoved(Optional.ofNullable(meta.getIsRemoved()).orElse(false));
		metaOutput.setName(Optional.ofNullable(meta.getName()).orElse(null));
		metaOutput.setDescription(Optional.ofNullable(meta.getDescription()).orElse(null));


		return metaOutput;
	}

	private ChatSettingsOutputDto getChatSettings(Chat chat) {
		ChatSettingsOutputDto settingsOutput = new ChatSettingsOutputDto();
		ChatSettings chatSettings = chat.getChatSettings();

		settingsOutput.setChatType(Optional.ofNullable(chatSettings.getChatType()).orElse(ChatType.PUBLIC));
		settingsOutput.setUsersLimit(Optional.ofNullable(chatSettings.getUsersLimit()).orElse(usersLimitFallback));
		settingsOutput.setIsGroupChat(Optional.ofNullable(chatSettings.getIsGroupChat()).orElse(false));

		return settingsOutput;
	}

	private ChatSecurityOutputDto getChatSecurity(Chat chat) {
		ChatSecurityOutputDto chatSecurityOutput = new ChatSecurityOutputDto();
		ChatSecurity chatSecurity = chat.getChatSecurity();

		chatSecurityOutput.setEncryptionSecret(chatSecurity.getEncryptionSecret());

		return chatSecurityOutput;
	}
}
