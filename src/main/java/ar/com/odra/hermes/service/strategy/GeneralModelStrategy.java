package ar.com.odra.hermes.service.strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.service.ModelSelectionStrategy;

 @Component
 @Order(99)
public class GeneralModelStrategy implements ModelSelectionStrategy {

	 
	 private final OllamaProperties properties;
	 
	 
	 
	public GeneralModelStrategy(OllamaProperties properties) {
		
		this.properties = properties;
	}

	@Override
	public boolean supports(AIRequest request) {
		
		
		 
		 return true;
	}

	@Override
	public String selectModel(AIRequest request) {
		
		return properties.getModel().getGeneral();
	}

}
