package ar.com.odra.hermes.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;
import ar.com.odra.hermes.exception.AIClientException;

@ExtendWith(MockitoExtension.class)
public class OllamaClientTest {

	@Mock
	private RestClient restClient;
	
	@Mock
	private RestClient.RequestBodyUriSpec requestBodyUriSpec;
	
	@Mock
	private RestClient.RequestBodySpec requestBodySpec;
	
	@Mock
	private RestClient.ResponseSpec responcseSpec;
	
	private OllamaClient ollamaClient;
	
	@BeforeEach
	void setUp() {
		ollamaClient = new OllamaClient(restClient);
	}
	
	@Test
	void shouldThrowAIClientExceptionWhenOllamaFails() {
		
		OllamaGenerateRequest request = 
				new OllamaGenerateRequest(
						"qwen2.5:3b",
						"¿Qué es Java?",
						false
						);
		
		when(restClient.post())
			.thenReturn(requestBodyUriSpec);
		
		when(requestBodyUriSpec.uri("/api/generate"))
			.thenReturn(requestBodySpec);
		
		when(requestBodySpec.body(any(OllamaGenerateRequest.class)))
			.thenReturn(requestBodySpec);
		
		when(requestBodySpec.retrieve())
			.thenReturn(responcseSpec);
		
		RuntimeException causa = 
				new RuntimeException("Ollama no responde");
		
		when(responcseSpec.body(OllamaGenerateResponse.class))
			.thenThrow(causa);
		
		AIClientException exception = assertThrows(
				AIClientException.class, () -> ollamaClient.generate(request)
				);
		
		assertEquals(
				"No fue posible comunicarse con Ollama.",
				exception.getMessage()
				);
		
		assertEquals(causa, exception.getCause());
		
	}
	
	
}















