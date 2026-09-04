package ar.com.odra.hermes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.odra.hermes.dto.ai.AIRequest;

@SpringBootTest
public class SpringModelSelectionStrategyTest {

	@Autowired
	private List<ModelSelectionStrategy> strategies;
	
	@Autowired
	public DefaultModelSelector modelSelector;
	


	
	@Test
	void springDeberiaOrdenarLasStrategiesSegunOrder() {
		
		assertEquals(3, strategies.size());
		
		assertEquals("ProgrammingModelStrategy", strategies.get(0).getClass().getSimpleName());
		
		assertEquals("CreativeModelStrategy", strategies.get(1).getClass().getSimpleName());
		
		assertEquals("GeneralModelStrategy", strategies.get(2).getClass().getSimpleName());
		
		
	}
	
	@Test
	void deberiaSeleccionarElModeloDeProgramacionUsandoBeansReales() {
		
		AIRequest request = new AIRequest ("¿Qué diferencia hay entre una interface y una clase abstracta en Java?");
		
		String selectedModel = modelSelector.selectModel(request);
		
		assertEquals("qwen2.5:3b", selectedModel);
	
		
		
	}
	
	
	
}
