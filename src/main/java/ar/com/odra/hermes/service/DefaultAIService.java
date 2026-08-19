package ar.com.odra.hermes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.com.odra.hermes.client.AIClient;
import ar.com.odra.hermes.dto.ai.AIRequest;
import ar.com.odra.hermes.dto.ai.AIResponse;
import ar.com.odra.hermes.dto.ai.OllamaGenerateRequest;
import ar.com.odra.hermes.dto.ai.OllamaGenerateResponse;

@Service
public class DefaultAIService implements AIService {

    private final AIClient aiClient;
    private final ModelSelector modelSelector;

    private static final Logger logger =
            LoggerFactory.getLogger(DefaultAIService.class);

    public DefaultAIService(
            AIClient aiClient,
            ModelSelector modelSelector) {

        this.aiClient = aiClient;
        this.modelSelector = modelSelector;
    }

    @Override
    public AIResponse ask(AIRequest request) {

        long inicio = System.currentTimeMillis();

        String model = modelSelector.selectModel(request);

        OllamaGenerateRequest ollamaRequest =
                new OllamaGenerateRequest(
                        model,
                        request.question(),
                        false
                );

        OllamaGenerateResponse ollamaResponse =
                aiClient.generate(ollamaRequest);

        long fin = System.currentTimeMillis();

        long tiempo = fin - inicio;

        logger.info(
                "Modelo {} respondió en {} ms",
                model,
                tiempo
        );

        return new AIResponse(
                ollamaResponse.response()
        );
    }
}