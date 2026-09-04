package ar.com.odra.hermes.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.odra.hermes.config.OllamaProperties;
import ar.com.odra.hermes.dto.ai.AIRequest;

@ExtendWith(MockitoExtension.class)
public class GeneralModelStrategyTest {

    @Mock
    private OllamaProperties properties;

    @Mock
    private OllamaProperties.Model model;

    @Test
    void deberiaReconocerCualquierPregunta() {

        AIRequest request =
                new AIRequest("¿Qué es el taoísmo?");

        GeneralModelStrategy strategy =
                new GeneralModelStrategy(null);

        assertTrue(strategy.supports(request));
    }

    @Test
    void deberiaSeleccionarElModeloGeneralConfigurado() {

        when(properties.getModel())
                .thenReturn(model);

        when(model.getGeneral())
                .thenReturn("llama3.2:latest");

        GeneralModelStrategy strategy =
                new GeneralModelStrategy(properties);

        AIRequest request =
                new AIRequest("¿Qué es el taoísmo?");

        String selectedModel =
                strategy.selectModel(request);

        assertEquals("llama3.2:latest", selectedModel);

        verify(properties).getModel();
        verify(model).getGeneral();
    }
}