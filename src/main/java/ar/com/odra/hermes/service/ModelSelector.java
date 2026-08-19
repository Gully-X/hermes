package ar.com.odra.hermes.service;

import ar.com.odra.hermes.dto.ai.AIRequest;

public interface ModelSelector {

	String selectModel(AIRequest request);
	
}
