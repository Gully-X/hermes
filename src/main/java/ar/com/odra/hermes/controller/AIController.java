package ar.com.odra.hermes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;
import ar.com.odra.hermes.service.AIService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai")
public class AIController {
	
	private final AIService aiService;
	
	public AIController(AIService aiService) {
		this.aiService = aiService;
	}
	
	@PostMapping("/ask")
	public AIResponse ask(@Valid @RequestBody AIRequest request) {
		
		return aiService.ask(request);
	}

}
