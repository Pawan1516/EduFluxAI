package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Course;
import com.EduFluxAI.backend.repository.CourseRepository;
import com.EduFluxAI.backend.dto.CourseFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = findById(id);
        
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setPrice(courseDetails.getPrice());
        course.setInstructor(courseDetails.getInstructor());
        course.setImageUrl(courseDetails.getImageUrl());
        course.setType(courseDetails.getType());
        course.setTags(courseDetails.getTags());
        course.setModules(courseDetails.getModules());
        course.setDownloads(courseDetails.getDownloads());
        
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        courseRepository.delete(course);
    }

    public List<Course> findCoursesWithFilters(CourseFilterRequest filters) {
        List<Course> courses = courseRepository.findAll();
        
        if (StringUtils.hasText(filters.getSearchTerm())) {
            String searchTerm = filters.getSearchTerm().toLowerCase();
            courses = courses.stream()
                .filter(course -> 
                    course.getTitle().toLowerCase().contains(searchTerm) ||
                    course.getDescription().toLowerCase().contains(searchTerm) ||
                    course.getInstructor().toLowerCase().contains(searchTerm)
                )
                .collect(Collectors.toList());
        }
        
        if (StringUtils.hasText(filters.getTag())) {
            courses = courses.stream()
                .filter(course -> course.getTags().contains(filters.getTag()))
                .collect(Collectors.toList());
        }
        
        if (StringUtils.hasText(filters.getPrice()) && !"all".equals(filters.getPrice())) {
            if ("free".equals(filters.getPrice())) {
                courses = courses.stream()
                    .filter(course -> course.getType() == Course.CourseType.FREE)
                    .collect(Collectors.toList());
            } else if ("paid".equals(filters.getPrice())) {
                courses = courses.stream()
                    .filter(course -> course.getType() == Course.CourseType.PAID)
                    .collect(Collectors.toList());
            }
        }
        
        return courses;
    }

    public List<String> getAllTags() {
        return courseRepository.findAll().stream()
            .flatMap(course -> course.getTags().stream())
            .distinct()
            .collect(Collectors.toList());
    }
}
