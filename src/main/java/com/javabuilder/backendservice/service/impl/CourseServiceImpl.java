package com.javabuilder.backendservice.service.impl;

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
}
