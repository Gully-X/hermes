package ar.com.odra.hermes.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import ar.com.odra.hermes.dto.ai.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;

public class GlobalExceptionHandlerTest {

	@Test
	void deberiaConstruirErrorDtoParaUnaExcepcionDeValidacion() {
		
		// Arrange
		
		GlobalExceptionHandler handler = new GlobalExceptionHandler();
		
		MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		FieldError fieldError = new FieldError(
				"aiRequest",
				"question",
				"La pregunta no puede estar vacía."
				);
		
		when(exception.getBindingResult()).thenReturn(bindingResult);
		
		when(bindingResult.getFieldError()).thenReturn(fieldError);
		
		when(request.getRequestURI()).thenReturn("/api/ai/ask");
		
		// Act
		
		var response = handler.handleValidationException(exception, request);
		
		
		// Assert
		
		
		assertEquals(400, response.getStatusCode().value());
		
		ErrorDTO error = response.getBody();
		
		assertEquals("VALIDATION_ERROR", error.code());
		
		assertEquals("La pregunta no puede estar vacía.", error.message());
		
		assertEquals("/api/ai/ask", error.path());
		
		
		
		
	}
	
	
}


















