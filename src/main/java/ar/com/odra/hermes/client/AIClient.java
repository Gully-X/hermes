package ar.com.odra.hermes.client;


import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;

public interface AIClient {

	OllamaGenerateResponse generate(OllamaGenerateRequest request);

	
	
	
	
}
