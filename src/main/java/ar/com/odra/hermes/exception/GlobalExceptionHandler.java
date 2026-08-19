package ar.com.odra.hermes.exception;

import ar.com.odra.hermes.dto.ai.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;



import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	private static final Logger logger =
	        LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationException(
			MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
	
		
		String message = ex.getBindingResult()
							.getFieldError()
							.getDefaultMessage();
		
		ErrorDTO error = new ErrorDTO( 
				
				LocalDateTime.now(),
				
				HttpStatus.BAD_REQUEST.value(),
				
				"VALIDATION_ERROR",
				
				message,
				
				request.getRequestURI()
				
				);
		
		logger.warn(
			    "VALIDATION_ERROR - path: {} - message: {}",
			    request.getRequestURI(),
			    message
			);
				
		
		return ResponseEntity
				.badRequest()
				.body(error);
		
	}
	
	@ExceptionHandler(AIClientException.class)
	public ResponseEntity<ErrorDTO> handleAIClientException(
	        AIClientException ex,
	        HttpServletRequest request) {

	    logger.error(
	            "AI_CLIENT_ERROR - path: {} - message: {}",
	            request.getRequestURI(),
	            ex.getMessage(),
	            ex
	    );

	    ErrorDTO error = new ErrorDTO(

	            LocalDateTime.now(),

	            HttpStatus.SERVICE_UNAVAILABLE.value(),

	            "AI_CLIENT_ERROR",

	            ex.getMessage(),

	            request.getRequestURI()

	    );

	    return ResponseEntity
	            .status(HttpStatus.SERVICE_UNAVAILABLE)
	            .body(error);
	}
	
	

}
