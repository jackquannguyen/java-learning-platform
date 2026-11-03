package com.javabuilder.backendservice.mapper;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toCourse(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .thumbnail(request.getThumbnail())
                .build();
    }

    public CreateCourseResponse toCreateCourseResponse(Course course) {
        return CreateCourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .thumbnail(course.getThumbnail())
                .createdAt(course.getCreatedAt())
                .build();
    }

    public CourseDetailResponse toCourseDetailResponse(Course course) {
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
}
