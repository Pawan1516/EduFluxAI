package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.entity.AILesson;
import com.EduFluxAI.backend.service.AILearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai/lessons")
@CrossOrigin("*")
public class AILearningController {

    @Autowired
    private AILearningService aiLearningService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AILesson>>> search(@RequestParam(required = false) String topic) {
        return ResponseEntity.ok(new ApiResponse<>(true, "AI lessons retrieved", aiLearningService.searchLessons(topic)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AILesson>> create(@RequestBody AILesson lesson) {
        return ResponseEntity.ok(new ApiResponse<>(true, "AI lesson created", aiLearningService.create(lesson)));
    }
}
