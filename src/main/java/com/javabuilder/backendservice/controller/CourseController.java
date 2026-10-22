package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.entity.Course;
import com.javabuilder.backendservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course newCourse) {
        return courseService.updateCourseById(id, newCourse);
    }

    @GetMapping("/search")
    public List<Course> searchCourse(@RequestParam String keyword) {
        return courseService.searchCourse(keyword);
    }


}
