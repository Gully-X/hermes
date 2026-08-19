package ar.com.odra.hermes.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;


@ExtendWith(MockitoExtension.class)
public class CreativeModelStrategyTest {

	@Mock
	private OllamaProperties properties;
	
	@Mock
	private OllamaProperties.Model model;
	
	@Test
	void deberiaReconocerUnaPreguntaCreativa() {
		
		AIRequest request = new AIRequest("Escribime un poema sobre el Tao");
		
		CreativeModelStrategy strategy = new CreativeModelStrategy(null);
		
		assertTrue(strategy.supports(request));
		
	}
	
	
	@Test
	void noDeberiaReconocerUnaPreguntaCreativa() {
		
		AIRequest request = new AIRequest("¿Que es el taoísmo?");
		
		CreativeModelStrategy strategy = new CreativeModelStrategy(null);
		
		assertFalse(strategy.supports(request));
		
	}
	
	@Test
	void deberiasSeleccionarElModeloCreativoConfigurado() {
		
		when(properties.getModel()).thenReturn(model);
		
		when(model.getCreative()).thenReturn("llama3.2:latest");
		
		CreativeModelStrategy strategy = new CreativeModelStrategy(properties);
		
		AIRequest request = new AIRequest("Escribime un poema sobre el Tao");
		
		String selectedModel = strategy.selectModel(request);
		
		assertEquals("llama3.2:latest", selectedModel);
		
		verify(properties).getModel();
		
		verify(model).getCreative();
		
		
		
	}
	
	
}






























