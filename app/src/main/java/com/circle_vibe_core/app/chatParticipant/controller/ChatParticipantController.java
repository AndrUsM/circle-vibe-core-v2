package com.circle_vibe_core.app.chatParticipant.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/chat-participant")
public class ChatParticipantController {
	@GetMapping("/list-paginated")
	public String getPaginated(@RequestParam String param) {
		return new String();
	}
}