package com.EduFluxAI.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Value("${openai.api.key:}")
    private String openAiKey;

    public String chat(String message) {
        // Placeholder until external LLM integration is configured
        if (message == null || message.isBlank()) {
            return "Ask me anything about courses, code, or AI!";
        }
        return "[AI] You said: " + message;
    }
}
