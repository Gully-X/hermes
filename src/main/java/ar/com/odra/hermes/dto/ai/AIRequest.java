package ar.com.odra.hermes.dto.ai;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AIRequest(
		
		@NotBlank(message = "La pregunta no puede estar vacía.")
		@Size(max = 1000, message ="La pregunta no puede superar los 1000 caracteres.")
		String question
		
		) {

}
