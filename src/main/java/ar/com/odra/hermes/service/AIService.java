package ar.com.odra.hermes.service;

import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;

public interface AIService {

	AIResponse ask(AIRequest question);
}
