package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.mapper.CourseMapper;
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
    private final CourseMapper courseMapper;

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request) {
        // 1. Convert request to entity
        Course course = courseMapper.toCourse(request);

        // 2. Save course to database
        courseRepository.save(course);

        // 3 Convert entity to response
        return  courseMapper.toCreateCourseResponse(course);
    }

    @Override
    public CourseDetailResponse findById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toCourseDetailResponse)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseDetailResponse> getCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toCourseDetailResponse)
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

        return courseMapper.toCourseDetailResponse(course);
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
                        .courseLevel(courseSummary.getCourseLevel())
                        .build())
                .toList();
    }
}
