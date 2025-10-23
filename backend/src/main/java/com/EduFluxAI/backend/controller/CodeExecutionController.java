package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.service.CodeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/run-code")
@CrossOrigin("*")
public class CodeExecutionController {

    public static class RunCodeRequest {
        public String language;
        public String code;
        public String input;
    }

    @Autowired
    private CodeExecutionService codeExecutionService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> run(@RequestBody RunCodeRequest req) {
        String result = codeExecutionService.runCode(req.language, req.code, req.input);
        return ResponseEntity.ok(new ApiResponse<>(true, "executed", result));
    }
}
