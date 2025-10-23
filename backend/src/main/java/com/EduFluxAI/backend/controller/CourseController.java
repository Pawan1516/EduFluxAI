package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.entity.Course;
import com.EduFluxAI.backend.service.CourseService;
import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.dto.CourseFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String price) {
        try {
            CourseFilterRequest filters = new CourseFilterRequest(searchTerm, tag, price);
            List<Course> courses = courseService.findCoursesWithFilters(filters);
            return ResponseEntity.ok(new ApiResponse<>(true, "Courses retrieved successfully", courses));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Course found", course));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody Course course) {
        try {
            Course createdCourse = courseService.save(course);
            return ResponseEntity.ok(new ApiResponse<>(true, "Course created successfully", createdCourse));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(id, course);
            return ResponseEntity.ok(new ApiResponse<>(true, "Course updated successfully", updatedCourse));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Course deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<ApiResponse<List<String>>> getCourseTags() {
        try {
            List<String> tags = courseService.getAllTags();
            return ResponseEntity.ok(new ApiResponse<>(true, "Tags retrieved successfully", tags));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}