package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Lesson;
import com.EduFluxAI.backend.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getLessonsForCourse(Long courseId) {
        return lessonRepository.findByCourseIdOrderByOrderIndexAsc(courseId);
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson.setTitle(lessonDetails.getTitle());
        lesson.setContent(lessonDetails.getContent());
        lesson.setVideoUrl(lessonDetails.getVideoUrl());
        lesson.setOrderIndex(lessonDetails.getOrderIndex());
        lesson.setDurationMinutes(lessonDetails.getDurationMinutes());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
