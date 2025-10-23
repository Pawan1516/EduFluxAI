package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    public static class ChatRequest { public String message; }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> chat(@RequestBody ChatRequest req) {
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", chatService.chat(req == null ? null : req.message)));
    }
}
