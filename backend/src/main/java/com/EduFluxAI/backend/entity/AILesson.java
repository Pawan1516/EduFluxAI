package com.EduFluxAI.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ai_lessons")
public class AILesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String topic;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String videoLink;

    @Column(name = "quiz_json", columnDefinition = "TEXT")
    private String quizJson;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getVideoLink() { return videoLink; }
    public void setVideoLink(String videoLink) { this.videoLink = videoLink; }
    public String getQuizJson() { return quizJson; }
    public void setQuizJson(String quizJson) { this.quizJson = quizJson; }
}
