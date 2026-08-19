package ar.com.odra.hermes.dto.ai;

public record OllamaGenerateResponse(
		
		String model,

		String created_at,

		String response,

		boolean done,

		long total_duration,

		long load_duration,

		long prompt_eval_count,

		long prompt_eval_duration,

		long eval_count,

		long eval_duration

		) 
	{

}
