package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.CourseSummary;
import com.javabuilder.backendservice.entity.Course;
import com.javabuilder.backendservice.repository.CourseRepository;
import com.javabuilder.backendservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        course.setCreatedAt(Instant.now());
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateCourseById(Long id, Course newCourse) {
        Course currentCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        currentCourse.setName(newCourse.getName());
        currentCourse.setDescription(newCourse.getDescription());
        currentCourse.setPrice(newCourse.getPrice());
        currentCourse.setThumbnail(newCourse.getThumbnail());
        currentCourse.setUpdatedAt(Instant.now());
        courseRepository.save(currentCourse);
        return currentCourse;
    }

    @Override
    public List<Course> searchCourse(String keyword) {
        List<CourseSummary> courseSummaries = courseRepository.findByNameOrDescription(keyword);
        return courseSummaries.stream()
                .map(courseSummary -> {
                    Course course = new Course();
                    course.setName(courseSummary.getName());
                    course.setDescription(courseSummary.getDescription());
                    course.setPrice(courseSummary.getPrice());
                    course.setThumbnail(courseSummary.getThumbnail());
                    return course;
                })
                .toList();
    }
}
