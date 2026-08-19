package ar.com.odra.hermes.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProgrammingModelStrategyTest {

	@Mock
	private OllamaProperties properties;
	
	@Mock
	private OllamaProperties.Model model;
	
	
	@Test
	void deberiaReconocerUnaPreguntaSobreJava() {
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		ProgrammingModelStrategy strategy = new ProgrammingModelStrategy(null);
		
		assertTrue(strategy.supports(request));
		
		
	}
	
	@Test
	void noDeberiaReconocerUnaPreguntaSobreJava() {
		
		AIRequest request = new AIRequest("¿Qué es el Taoísmo?");
		
		ProgrammingModelStrategy strategy = new ProgrammingModelStrategy(null);
		
		assertFalse(strategy.supports(request));
		
		
	}
	
	@Test
	void deberiasSeleccionarElModeloDeProgramacionConfigurado() {
		
		when(properties.getModel()).thenReturn(model);
		
		when(model.getProgramming()).thenReturn("qwen2.5:3b");
		
		ProgrammingModelStrategy strategy = new ProgrammingModelStrategy(properties);
		
		AIRequest request = new AIRequest("¿Qué es Java?");
		
		String selectedModel = strategy.selectModel(request);
		
		assertEquals("qwen2.5:3b", selectedModel);
		
		verify(properties).getModel();
		
		verify(model).getProgramming();
		
		
		
	}
	
}


















