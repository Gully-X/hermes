package ar.com.odra.hermes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import ar.com.odra.hermes.client.AIClient;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;
import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;

@SpringBootTest
public class SpringAIServiceIntegrationTest {

	
	@Autowired
	private AIService aiService;
	
	@MockitoBean
	private AIClient aiClient;
	
	@Test
	void deberiaUtilizarElAIClienteInyectadoPorSpring() {
		
		// Arrange
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		OllamaGenerateResponse ollamaResponse = new OllamaGenerateResponse(
				"qwen2.5:3b",
				 "2026-08-29T10:00:00Z",
                 "Java es un lenguaje de programación.",
                 true,
                 1000000,
                 100000,
                 10,
                 200000,
                 20,
                 700000
			);
		
		when(aiClient.generate(any(OllamaGenerateRequest.class))).thenReturn(ollamaResponse);
		
		// Act
		
		AIResponse result = aiService.ask(request);
		
		// Assert
		
		
		
		assertEquals("Java es un lenguaje de programación.", result.answer()
				);
		
	}
	
	@Test
	void deberiaConstruirCorrectamenteElOllamaGenerateRequest() {
		
		// Arrange
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		OllamaGenerateResponse ollamaResponse = new OllamaGenerateResponse(
				"qwen2.5:3b",
				 "2026-08-29T10:00:00Z",
                 "Java es un lenguaje de programación.",
                 true,
                 1000000,
                 100000,
                 10,
                 200000,
                 20,
                 700000);
		
		when(aiClient.generate(any(OllamaGenerateRequest.class))).thenReturn(ollamaResponse);
		
		// Act
		
		aiService.ask(request);
		
		// Assert
		
		ArgumentCaptor<OllamaGenerateRequest> captor = ArgumentCaptor.forClass(OllamaGenerateRequest.class);
		
		verify(aiClient).generate(captor.capture());
		
		OllamaGenerateRequest captureRequest = captor.getValue();
		
		assertEquals("qwen2.5:3b", captureRequest.model());
		
		assertEquals("¿Qué es Java?", captureRequest.prompt());
		
		assertFalse(captureRequest.stream());
		
		
	}
}




















