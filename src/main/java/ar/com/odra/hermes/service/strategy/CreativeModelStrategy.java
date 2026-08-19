package ar.com.odra.hermes.service.strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.service.ModelSelectionStrategy;

@Component
@Order(2)
public class CreativeModelStrategy implements ModelSelectionStrategy {

	 private final OllamaProperties properties;
	 
	 
	 
	
	public CreativeModelStrategy(OllamaProperties properties) {
		this.properties = properties;
	}

	@Override
	public boolean supports(AIRequest request) {
		
		String question = request.question().toLowerCase();
		
		
		
		return question.contains("poema")
				|| question.contains("cuento")
				|| question.contains("historia")
				|| question.contains("metáfora");
	}

	@Override
	public String selectModel(AIRequest request) {
		
		return properties.getModel().getCreative();
	}

}
