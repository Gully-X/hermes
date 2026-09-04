package ar.com.odra.hermes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.odra.hermes.client.AIClient;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;
import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;
import ar.com.odra.hermes.exception.AIClientException;

@ExtendWith(MockitoExtension.class)
public class DefaultAIServiceTest {
	
	
	@Mock
	private AIClient aiClient;
	
	@Mock
	private ModelSelector modelSelector;
	
	@Test
	void deberiaProcesarUnaPreguntaCorrectamente() {
		
		// Arrange
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		when(modelSelector.selectModel(request)).thenReturn("qwen2.5:3b");
		
		OllamaGenerateResponse ollamaResponse = new OllamaGenerateResponse (
				
				 "qwen2.5:3b",
                 "2026-08-30T10:00:00Z",
                 "Java es un lenguaje de programación.",
                 true,
                 1000,
                 100,
                 10,
                 200,
                 20,
                 700
				
			);
		
		when(aiClient.generate(any(OllamaGenerateRequest.class)))
				.thenReturn(ollamaResponse);
		
		DefaultAIService service = new DefaultAIService(
				
					aiClient,
					modelSelector
				
				);
		
		// Act
		
		AIResponse result = service.ask(request);
		
		// Assert
		
		assertEquals(
				
				"Java es un lenguaje de programación.", result.answer()
				
				);
		
		verify(modelSelector).selectModel(request);
		
		verify(aiClient).generate(any(OllamaGenerateRequest.class));
		
		
		
	}
	
	@Test
	void deberiaConstruirCorrectamenteElRequestParaOllama() {
		
		// Arrange
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		when(modelSelector.selectModel(request)).thenReturn("qwen2.5:3b");
		
		OllamaGenerateResponse ollamaGenerateResponse = new OllamaGenerateResponse( 
				
				  "qwen2.5:3b",
                  "2026-08-30T10:00:00Z",
                  "Java es un lenguaje de programación.",
                  true,
                  1000,
                  100,
                  10,
                  200,
                  20,
                  700
				
				
			);
		
		when(aiClient.generate(any(OllamaGenerateRequest.class))).thenReturn(ollamaGenerateResponse);
		
		DefaultAIService service = new DefaultAIService(
							aiClient,
							modelSelector
					
				);
		
		// Act
		
		service.ask(request);
		
		// Assert
		
		ArgumentCaptor<OllamaGenerateRequest> captor = ArgumentCaptor.forClass(OllamaGenerateRequest.class);
		
		verify(aiClient).generate(captor.capture());
		
		OllamaGenerateRequest captureRequest = captor.getValue();
		
		
		assertEquals("qwen2.5:3b", captureRequest.model());
		
		assertFalse(captureRequest.stream());
				
		
	}
	
	@Test
	void deberiaPropagarAIClientException() {
		
		// Arrange
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		when(modelSelector.selectModel(request)).thenReturn("qwen2.5:3b");
		
		when(aiClient.generate(any(OllamaGenerateRequest.class)))
				.thenThrow(
						new AIClientException("No fue posible comunicarse con Ollama."
								)
						);
		
		DefaultAIService service = 
				new DefaultAIService (
						aiClient,
						modelSelector
				);
		
		// Act + Assert
		
		AIClientException exception = 
				
				assertThrows(
						AIClientException.class, () -> service.ask(request)
						);
		
		assertEquals(
				"No fue posible comunicarse con Ollama.", exception.getMessage()
				);
		
		
	}

}
















