package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.entity.Challenge;
import com.EduFluxAI.backend.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
@CrossOrigin("*")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Challenge>>> all() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Challenges retrieved", challengeService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Challenge>> get(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Challenge found", challengeService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Challenge>> create(@RequestBody Challenge challenge) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Challenge created", challengeService.save(challenge)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Challenge>> update(@PathVariable Long id, @RequestBody Challenge challenge) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Challenge updated", challengeService.update(id, challenge)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        challengeService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Challenge deleted", null));
    }
}
