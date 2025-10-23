package com.codeplatform.controller;

import com.codeplatform.config.JwtUtil;
import com.codeplatform.model.*;
import com.codeplatform.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    public AuthController(AuthService authService, JwtUtil jwtUtil) { this.authService = authService; this.jwtUtil = jwtUtil; }

    record RegisterRequest(String email, String password, String username, String role) {}
    record AuthResponse(String token) {}

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        User u = authService.register(req.email(), req.password(), req.role() == null ? "STUDENT" : req.role(), req.username());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", u.getRole());
        String token = jwtUtil.generate(u.getEmail(), claims);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

@RestController
@RequestMapping("/api/courses")
class CourseController {
    private final CourseService service;
    public CourseController(CourseService service) { this.service = service; }
    @GetMapping public List<Course> all() { return service.all(); }
    @PostMapping public Course create(@RequestBody Course course) { return service.create(course); }
}

@RestController
@RequestMapping("/api/challenges")
class ChallengeController {
    private final ChallengeService service;
    public ChallengeController(ChallengeService service) { this.service = service; }
    @GetMapping public List<Challenge> all() { return service.all(); }
}

@RestController
@RequestMapping("/api/ai/lessons")
class AILearningController {
    private final AILearningService service;
    public AILearningController(AILearningService service) { this.service = service; }
    @GetMapping public List<AILesson> all() { return service.all(); }
}


