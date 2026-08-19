package ar.com.odra.hermes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import ar.com.odra.hermes.dto.ai.AIRequest;


@ExtendWith(MockitoExtension.class)
public class DefaultModelSelectorTest {
	
	
	@Mock
	private ModelSelectionStrategy strategy;
	
	@Mock
	private AIRequest request;
	
	@Test
	void deberiaSeleccionarLaStrategyCorrecta() {
		
		DefaultModelSelector selector = new DefaultModelSelector(List.of(strategy));
		
		when(strategy.supports(request)).thenReturn(true);
		
		when(strategy.selectModel(request)).thenReturn("qwen2.5:3b");
		
		// Act
		
		String model = selector.selectModel(request);
		
		// Assert
		
		assertEquals("qwen2.5:3b", model);
		
		
	}
	
	@Test
	void deberiaLanzarExcepcionSiNingunaStrategyCoincide() {

	    // Arrange

	    DefaultModelSelector selector =
	            new DefaultModelSelector(
	                    List.of(strategy)
	            );

	    when(strategy.supports(request))
	            .thenReturn(false);

	    // Act + Assert

	    assertThrows(
	            IllegalStateException.class,
	            () -> selector.selectModel(request)
	    );
	    
	    
	    // Verify
	    
	    verify(strategy).supports(request);
	    
	    verify(strategy, never()).selectModel(request);
	    
	    
	    
	}
	
	@Test 
	void deberiaConsultarYSeleccionarLaStrategyCorrecta() {

		
		// Arrange
		
		DefaultModelSelector selector = new DefaultModelSelector(List.of(strategy));
		
		when(strategy.supports(request)).thenReturn(true);
		
		when(strategy.selectModel(request)).thenReturn("qwen2.5:3b");
		
		// Act
		
		String model = selector.selectModel(request);
		
		// Assert
		
		assertEquals("qwen2.5:3b", model);
		
		verify(strategy).supports(request);
		
		verify(strategy).selectModel(request);
		
		
	}
	
	@Test
	void deberiaConsultarLaStrategyYSeleccionarElModelo() {
		
		// Arrange
		
		DefaultModelSelector selector = new DefaultModelSelector(List.of(strategy));
		
		when(strategy.supports(request)).thenReturn(true);
		
		when(strategy.selectModel(request)).thenReturn("qwen2.5:3b");
		
		// Act
		
		String model = selector.selectModel(request);
		
		// Assert
		
		assertEquals("qwen2.5:3b", model);
		
		// Verify
		
		verify(strategy).supports(request);
		
		verify(strategy).supports(request);
		
		
	}
	
	

}































