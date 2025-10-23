package com.codeplatform.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "streaks")
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @Column
    private Integer count;

    @Column
    private LocalDate lastActive;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
    public LocalDate getLastActive() { return lastActive; }
    public void setLastActive(LocalDate lastActive) { this.lastActive = lastActive; }
}


