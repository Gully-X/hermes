package ar.com.odra.hermes.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.odra.hermes.client.AIClient;

@SpringBootTest
public class SpringAIServiceTest {

	@Autowired
	private AIService aiService;
	
	@Autowired
	private AIClient aiClient;
	
	@Autowired
	private ModelSelector modelSelector;
	
	@Test
	void springDeberiaConstruirElAIService() {
		
		assertNotNull(aiService);
		
		assertInstanceOf(DefaultAIService.class, aiService);
		
	}
	
	@Test
	void springDeberiaResolverLasDependenciasDelAIService() {
		
		assertNotNull(aiClient);
		assertNotNull(modelSelector);
		
		assertInstanceOf(DefaultModelSelector.class, modelSelector);
		
	}
	
	
}
