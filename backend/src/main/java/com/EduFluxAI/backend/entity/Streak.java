package com.EduFluxAI.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "streaks")
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "count", nullable = false)
    private Integer count = 0;

    @Column(name = "last_active")
    private LocalDate lastActive;

    public Streak() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
    public LocalDate getLastActive() { return lastActive; }
    public void setLastActive(LocalDate lastActive) { this.lastActive = lastActive; }
}
