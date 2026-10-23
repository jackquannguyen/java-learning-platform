package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseResponse;
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
        Course course = Course.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                                .price(request.getPrice())
                                        .thumbnail(request.getThumbnail())
                .createdAt(Instant.now())
                                                .build();
         courseRepository.save(course);

         return CreateCourseResponse.builder().id(course.getId()).name(course.getName()).description(course.getDescription()).price(course.getPrice()).thumbnail(course.getThumbnail()).createdAt(course.getCreatedAt()).build();
    }

    @Override
    public CourseResponse findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Course found!"));
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .thumbnail(course.getThumbnail())
                .build();
    }

    @Override
    public List<CourseResponse> getCourses() {
        return courseRepository.findAll().stream().map(
                course -> CourseResponse.builder().id(course.getId()).name(course.getName()).description(course.getDescription()).price(course.getPrice()).thumbnail(course.getThumbnail()).createdAt(course.getCreatedAt()).build()
        ).toList();
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponse updateCourseById(Long id, UpdateCourseRequest updateRequest) {
        Course currentCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseResponse response = CourseResponse.builder()
                .id(currentCourse.getId())
                .name(updateRequest.getName())
                .description(updateRequest.getDescription())
                .price(updateRequest.getPrice())
                .thumbnail(updateRequest.getThumbnail())
                .createdAt(currentCourse.getCreatedAt())
                .updatedAt(Instant.now())
                .build();

        courseRepository.save(
                currentCourse.builder()
                        .name(response.getName())
                        .description(response.getDescription())
                        .price(response.getPrice())
                        .thumbnail(response.getThumbnail())
                        .updatedAt(response.getUpdatedAt())
                        .build()
        );

        return response;
    }

    @Override
    public List<CourseResponse> searchCourse(String keyword) {
        List<CourseSummary> courseSummaries = courseRepository.findByNameOrDescription(keyword);
        return courseSummaries.stream()
                .map(courseSummary -> CourseResponse.builder()
                        .name(courseSummary.getName())
                        .description(courseSummary.getDescription())
                        .price(courseSummary.getPrice())
                        .thumbnail(courseSummary.getThumbnail())
                        .build()
                )
                .toList();
    }
}
