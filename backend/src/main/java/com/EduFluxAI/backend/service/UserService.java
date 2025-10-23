package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.User;
import com.EduFluxAI.backend.repository.UserRepository;
import com.EduFluxAI.backend.dto.LoginRequest;
import com.EduFluxAI.backend.dto.LoginResponse;
import com.EduFluxAI.backend.dto.RegisterRequest;
import com.EduFluxAI.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.STUDENT);
        user.setIsVerified(false);

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOptional.get();
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!user.getIsVerified()) {
            throw new RuntimeException("Account not verified. Please check your email for verification.");
        }

        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token, user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = findById(id);
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setExternalAccounts(userDetails.getExternalAccounts());
        user.setEnrolledCourses(userDetails.getEnrolledCourses());
        
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}

