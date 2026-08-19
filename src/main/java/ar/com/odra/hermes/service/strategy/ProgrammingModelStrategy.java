package ar.com.odra.hermes.service.strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.service.ModelSelectionStrategy;

@Component
@Order(1)
public class ProgrammingModelStrategy implements ModelSelectionStrategy {

	
	private final OllamaProperties properties;
	
	private static final Logger logger = LoggerFactory.getLogger(ProgrammingModelStrategy.class);
	
	public ProgrammingModelStrategy(OllamaProperties properties) {
		this.properties = properties;
	}
	
    @Override
    public boolean supports(AIRequest request) {

        String question = request.question().toLowerCase();

        return question.contains("java")
                || question.contains("spring")
                || question.contains("programacion")
                || question.contains("programar");
    }

    @Override
    public String selectModel(AIRequest request) {

    	String model = properties.getModel().getProgramming();
    	
    	logger.info("Modelo de programacion configurado: {}", model);
    	
        return model;
    }
}