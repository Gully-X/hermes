package ar.com.odra.hermes.dto.ai;

import java.time.LocalDateTime;

public record ErrorDTO( 
	
	LocalDateTime timestamp,
	
	int status,
	
	String code,
	
	String message,
	
	String path
	
	)

{}
