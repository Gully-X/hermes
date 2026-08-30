package ar.com.odra.hermes.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;
import ar.com.odra.hermes.service.AIService;

@WebMvcTest(AIController.class)
public class AIControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private AIService aiService;
	
	@Test
	void deberiaResponde200CuandoLaPreguntaEsValida() throws Exception {
		
		// Arrange
		
		when(aiService.ask(any(AIRequest.class)))
			.thenReturn(new AIResponse("Java es un lenguaje de programación."));
		
		// Act + Assert
		
		mockMvc.perform(post("/api/ai/ask")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"question": "¿Qué es Java?"
						}
						"""))
				.andExpect(status().isOk())
				.andExpect(
						content().json("""
									{
										"answer": "Java es un lenguaje de programación."
									}
								"""));
	}

	
	@Test
	void deberiaResponder400CuandoLaPreguntaEstaVacia() throws Exception {
		
		// Act + Assert
		
		mockMvc.perform(
				post("/api/ai/ask")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							{ 
								"question": ""
							}
							""")
				)
				.andExpect(status().isBadRequest());
		
		// Assert
		
		verify(aiService, never())
				.ask(any(AIRequest.class));
		
	}
	
	@Test
	void deberiaResponder400CuandoLaPreguntaSuperarLos1000Caracteres() throws Exception {
		
		// Arrange
		
		String pregunta = "a".repeat(1001);
		
		// Act + Assert
		
		mockMvc.perform(
				post("/api/ai/ask")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							{
								"question": "%s"
							}
							""".formatted(pregunta))
				)
				.andExpect(status().isBadRequest());
		
		// Assert
		
		verify(aiService, never())
			.ask(any(AIRequest.class));
		
	}
	
	@Test
	void deberiaAceptarUnaPreguntaDe100Caracteres() throws Exception {
		
		
		// Arrange
		
		String pregunta ="a".repeat(1000);
		
		when(aiService.ask(any(AIRequest.class)))
			.thenReturn(
					new AIResponse("Preguta válida.")
					);
		
		// Act + Assert
		
		mockMvc.perform(
				post("/api/ai/ask")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							{
								"question": "%s"
							}
							""".formatted(pregunta))
				)
				.andExpect(status().isOk());
		
				verify(aiService)
					.ask(any(AIRequest.class));
		
		
	}
	
}
















