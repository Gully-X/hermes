package ar.com.odra.hermes.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ar.com.odra.hermes.dto.ai.AIRequest;


@Service
public class DefaultModelSelector implements ModelSelector {
	
	

	
	private final List<ModelSelectionStrategy> strategies;
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultModelSelector.class);

	public DefaultModelSelector (List<ModelSelectionStrategy> strategies) {
		
		this.strategies = strategies;
		
		 logger.info(
			        "Strategies recibidas por Spring: {}",
			        strategies.size()
			    );
		
	}
	
	
	@Override
	public String selectModel(AIRequest request) {
		
		logger.info("Cantidad de Strategies disponibles: {}", strategies.size());
		
		for (ModelSelectionStrategy strategy : strategies) {
			
			
			
			 logger.info(
		                "Evaluando Strategy: {}",
		                strategy.getClass().getSimpleName());
			
			
			if (strategy.supports(request)) {
				
				  logger.info(
			                "Strategy seleccionada: {}",
			                strategy.getClass().getSimpleName()
			        );

				
				return strategy.selectModel(request);
			}
			
		}
		
		throw new IllegalStateException("No existe una ModelSelectionStrategy disponible");
				
		
	}

}
