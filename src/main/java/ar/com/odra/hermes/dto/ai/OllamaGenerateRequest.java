package ar.com.odra.hermes.dto.ai;

public record OllamaGenerateRequest(
		
		String model,
		
		String prompt,
		
		boolean stream
		
		) {

}
