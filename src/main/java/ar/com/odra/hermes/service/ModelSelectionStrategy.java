package ar.com.odra.hermes.service;

import ar.com.odra.hermes.dto.ai.AIRequest;

public interface ModelSelectionStrategy {

    boolean supports(AIRequest request);

    String selectModel(AIRequest request);
    
   
}