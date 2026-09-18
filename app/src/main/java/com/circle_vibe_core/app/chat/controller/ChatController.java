package com.circle_vibe_core.app.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.circle_vibe_core.app.chat.dto.ChatInputDto;
import com.circle_vibe_core.app.chat.entity.Chat;
import com.circle_vibe_core.app.chat.params.ChatFilterParams;
import com.circle_vibe_core.app.chat.service.ChatBaseService;
import com.circle_vibe_core.app.chat.service.ChatQueryService;
import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/chats")
public class ChatController {
	private final ChatBaseService chatBaseService;
	private final ChatQueryService chatQueryService;

	public ChatController(ChatBaseService chatBaseService, ChatQueryService chatQueryService) {
		this.chatBaseService = chatBaseService;
		this.chatQueryService = chatQueryService;
	}

	@Operation(summary = "Create chat")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Chat is created"),
			@ApiResponse(responseCode = "400", description = "Incorrect payload")
	})
	@PostMapping()
	public Chat createChat(@Valid @RequestBody ChatInputDto chatInputDto) {
		return this.chatBaseService.create(chatInputDto);
	}

	@Operation(summary = "Update chat")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Chat is created"),
			@ApiResponse(responseCode = "400", description = "Incorrect payload")
	})
	@PutMapping("/{id}")
	public Chat updateChat(@PathVariable("id") Long chatId, @Valid @RequestBody ChatInputDto chatInputDto) {
		Chat currentEntity = this.getChatById(chatId);
		return this.chatBaseService.update(chatId, currentEntity, chatInputDto);
	}

	@Operation(summary = "Delete chat")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Delete chat by id"),
			@ApiResponse(responseCode = "404", description = "Not found")
	})
	@DeleteMapping("/{id}")
	public int deleteChatById(@PathVariable("id") Long chatId) {
		return this.chatBaseService.deteteById(chatId);
	}

	@Operation(summary = "Get by ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Get chat by ID"),
			@ApiResponse(responseCode = "400", description = "Incorrect payload")
	})
	@GetMapping("/{id}")
	public Chat getChatById(@PathVariable("id") Long chatId) {
		return this.chatQueryService.getById(chatId);
	}

	@Operation(summary = "List of chats")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "List of charts"),
			@ApiResponse(responseCode = "400", description = "Incorrect payload")
	})
	@GetMapping("/list")
	public List<Chat> getChats(@ParameterObject @ModelAttribute ChatFilterParams filters) {
		return this.chatQueryService.getAll(filters);
	}

	@Operation(summary = "Paginated list of chats")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Chat is created"),
			@ApiResponse(responseCode = "400", description = "Incorrect payload")
	})
	@GetMapping("/paginated")
	public Page<Chat> getPaginatedChats(@ParameterObject @ModelAttribute ChatFilterParams filters,
			@ParameterObject @ModelAttribute PaginationParamsDto paginationDto) {
		return this.chatQueryService.getAllPaginated(paginationDto, filters);
	}
}
