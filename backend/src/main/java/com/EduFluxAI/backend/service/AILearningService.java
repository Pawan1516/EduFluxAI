package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.AILesson;
import com.EduFluxAI.backend.repository.AILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AILearningService {

    @Autowired
    private AILessonRepository aiLessonRepository;

    public List<AILesson> searchLessons(String topic) {
        if (topic == null || topic.isBlank()) {
            return aiLessonRepository.findAll();
        }
        return aiLessonRepository.findByTopicContainingIgnoreCase(topic);
    }

    public AILesson create(AILesson lesson) { return aiLessonRepository.save(lesson); }
}
