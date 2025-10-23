package com.EduFluxAI.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.STUDENT;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "user_enrolled_courses", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "course_id")
    private List<String> enrolledCourses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_external_accounts", joinColumns = @JoinColumn(name = "user_id"))
    private List<ExternalAccount> externalAccounts = new ArrayList<>();

    public enum Role {
        ADMIN, STUDENT
    }

    @Embeddable
    public static class ExternalAccount {
        private String platform;
        private String username;
        private Integer solvedCount;
        private String profileUrl;

        // Constructors
        public ExternalAccount() {}

        public ExternalAccount(String platform, String username, Integer solvedCount, String profileUrl) {
            this.platform = platform;
            this.username = username;
            this.solvedCount = solvedCount;
            this.profileUrl = profileUrl;
        }

        // Getters and Setters
        public String getPlatform() { return platform; }
        public void setPlatform(String platform) { this.platform = platform; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public Integer getSolvedCount() { return solvedCount; }
        public void setSolvedCount(Integer solvedCount) { this.solvedCount = solvedCount; }
        public String getProfileUrl() { return profileUrl; }
        public void setProfileUrl(String profileUrl) { this.profileUrl = profileUrl; }
    }

    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User(String name, String email, String password, Role role) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(List<String> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    public List<ExternalAccount> getExternalAccounts() { return externalAccounts; }
    public void setExternalAccounts(List<ExternalAccount> externalAccounts) { this.externalAccounts = externalAccounts; }
}

