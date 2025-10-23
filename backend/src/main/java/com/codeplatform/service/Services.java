package com.codeplatform.service;

import com.codeplatform.model.*;
import com.codeplatform.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users; this.encoder = encoder;
    }
    public User register(String email, String password, String role, String username) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        u.setRole(role);
        u.setUsername(username);
        return users.save(u);
    }
}

@Service
class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) { this.repo = repo; }
    public List<Course> all() { return repo.findAll(); }
    public Course create(Course c) { return repo.save(c); }
}

@Service
class ChallengeService {
    private final ChallengeRepository repo;
    public ChallengeService(ChallengeRepository repo) { this.repo = repo; }
    public List<Challenge> all() { return repo.findAll(); }
}

@Service
class PaymentService {
    private final PaymentRepository repo;
    public PaymentService(PaymentRepository repo) { this.repo = repo; }
}

@Service
class AILearningService {
    private final AILessonRepository repo;
    public AILearningService(AILessonRepository repo) { this.repo = repo; }
    public List<AILesson> all() { return repo.findAll(); }
}


