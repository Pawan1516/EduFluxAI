package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.entity.Lesson;
import com.EduFluxAI.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin("*")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<Lesson>>> getLessons(@PathVariable Long courseId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lessons retrieved", lessonService.getLessonsForCourse(courseId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Lesson>> create(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lesson created", lessonService.createLesson(lesson)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Lesson>> update(@PathVariable Long id, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lesson updated", lessonService.updateLesson(id, lesson)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lesson deleted", null));
    }
}
