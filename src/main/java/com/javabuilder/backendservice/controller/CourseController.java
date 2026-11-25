package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CreateCourseResponse createCourse(@RequestBody CreateCourseRequest request) {
        return courseService.createCourse(request);
    }

    @GetMapping("/{id}")
    public CourseDetailResponse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public List<CourseDetailResponse> getCourses() {
        return courseService.getCourses();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CourseDetailResponse updateCourse(@PathVariable Long id, @RequestBody UpdateCourseRequest request) {
        return courseService.updateCourseById(id, request);
    }

    @GetMapping("/search")
    public List<CourseDetailResponse> searchCourse(@RequestParam String keyword) {
        return courseService.searchCourse(keyword);
    }
}
