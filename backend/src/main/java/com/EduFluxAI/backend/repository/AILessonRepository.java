package com.EduFluxAI.backend.repository;

import com.EduFluxAI.backend.entity.AILesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AILessonRepository extends JpaRepository<AILesson, Long> {
    List<AILesson> findByTopicContainingIgnoreCase(String topic);
}
