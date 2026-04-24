package com.example.whatsappchatbot.controller;

import com.example.whatsappchatbot.model.IncomingMessage;
import com.example.whatsappchatbot.model.OutgoingMessage;
import com.example.whatsappchatbot.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/webhook")
public class WebhookController {

	private final ChatbotService chatbotService;

	public WebhookController(ChatbotService chatbotService) {
		this.chatbotService = chatbotService;
	}

	@PostMapping
	public ResponseEntity<OutgoingMessage> receiveMessage(@RequestBody IncomingMessage incomingMessage) {
		String reply = chatbotService.getReply(incomingMessage.getSender(), incomingMessage.getMessage());
		return ResponseEntity.ok(new OutgoingMessage(reply));
	}
}
