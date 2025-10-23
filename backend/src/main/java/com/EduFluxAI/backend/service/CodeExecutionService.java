package com.EduFluxAI.backend.service;

import org.springframework.stereotype.Service;

@Service
public class CodeExecutionService {

    public String runCode(String language, String code, String input) {
        // Mock implementation for now; plug into sandbox later
        return "[" + language + "] Executed mock with input: " + (input == null ? "" : input);
    }
}
