package ar.com.odra.hermes.client;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;
import tools.jackson.databind.ObjectMapper;

public class OllamaGenerateResponseTest {

	//Test #35
	@Test
	void deberiaFallarCuandoJacksonNoPuedeDeserializarLaRespuesta() throws Exception {
		
		
		// Arrange
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String json = """
				
				{
					"model": "qwen2.5:3b",
					"response": {
						"texto": "Java es un lenguaje de programación."
					},
					"done": true
					
				}
				
				
				""";
		
		// Act & Assert
		
		assertThrows(
				JacksonException.class,
				() -> objectMapper.readValue(
						json,
						OllamaGenerateResponse.class
						)
				);
		
		
	}
	
}
