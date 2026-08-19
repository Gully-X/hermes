package ar.com.odra.hermes.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;
import ar.com.odra.hermes.exception.AIClientException;

@Service
public class OllamaClient implements AIClient {
	
	private final RestClient restClient;

	
	
	private static final Logger logger = LoggerFactory.getLogger(OllamaClient.class);
	
	
	public OllamaClient (RestClient restClient) {
		
		
		this.restClient = restClient;
	}


	@Override
	public OllamaGenerateResponse generate(OllamaGenerateRequest request) {
		
		logger.info("Enviando solicitud hacia Ollama...");
		
		long inicio = System.currentTimeMillis();
		
		logger.info(
			    "Modelo: {}, Prompt: {}, Stream: {}",
			    request.model(),
			    request.prompt(),
			    request.stream()
			);
		
		try {
		
		OllamaGenerateResponse response = restClient.post()
						  .uri("/api/generate")
						  .body(request)
						  .retrieve()
						  .body(OllamaGenerateResponse.class);
		
		long fin = System.currentTimeMillis();
		
		logger.info(
			    "Ollama - total: {} ms, load: {} ms, prompt: {} ms, eval: {} ms, tokens: {}",
			    response.total_duration() / 1_000_000,
			    response.load_duration() / 1_000_000,
			    response.prompt_eval_duration() / 1_000_000,
			    response.eval_duration() / 1_000_000,
			    response.eval_count()
			);
		
		logger.info("Respuesta recibida desde Ollama {} ms", fin - inicio);
		
		
		
		return response;
		
		
		} catch (Exception ex) {
			
			
			logger.error(
					
					"Error al comunicarse con Ollama", ex
					
					);
			
			throw new AIClientException("No fue posible comunicarse con Ollama.", ex);
			
		}
				
	}





	
	
	


}
