package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.repository.projection.CourseSummary;
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
    public CreateCourseResponse createCourse(CreateCourseRequest request) {
        // 1. Convert request to entity
        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .thumbnail(request.getThumbnail())
                .build();

        // 2. Save course to database
        courseRepository.save(course);

        // 3 Convert entity to response
        return CreateCourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .thumbnail(course.getThumbnail())
                .createdAt(course.getCreatedAt())
                .build();
    }

    @Override
    public CourseDetailResponse findById(Long id) {
        return courseRepository.findById(id)
                .map(course -> CourseDetailResponse.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .description(course.getDescription())
                        .price(course.getPrice())
                        .thumbnail(course.getThumbnail())
                        .createdAt(course.getCreatedAt())
                        .updatedAt(course.getUpdatedAt())
                        .build())
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseDetailResponse> getCourses() {
        return courseRepository.findAll().stream()
                .map(course -> CourseDetailResponse.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .description(course.getDescription())
                        .price(course.getPrice())
                        .thumbnail(course.getThumbnail())
                        .createdAt(course.getCreatedAt())
                        .updatedAt(course.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDetailResponse updateCourseById(Long id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if(request.getName() != null) {
            course.setName(request.getName());
        }

        if(request.getDescription() != null) {
            course.setDescription(request.getDescription());
        }

        if(request.getPrice() != null) {
            course.setPrice(request.getPrice());
        }

        if(request.getThumbnail() != null) {
            course.setThumbnail(request.getThumbnail());
        }
        course.setUpdatedAt(Instant.now());
        courseRepository.save(course);

        return CourseDetailResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .thumbnail(course.getThumbnail())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    @Override
    public List<CourseDetailResponse> searchCourse(String keyword) {
        List<CourseSummary> courseSummaries = courseRepository.findByNameOrDescription(keyword);
        return courseSummaries.stream()
                .map(courseSummary -> CourseDetailResponse.builder()
                        .name(courseSummary.getName())
                        .description(courseSummary.getDescription())
                        .price(courseSummary.getPrice())
                        .thumbnail(courseSummary.getThumbnail())
                        .createdAt(courseSummary.getCreatedAt())
                        .updatedAt(courseSummary.getUpdatedAt())
                        .build())
                .toList();
    }
}
