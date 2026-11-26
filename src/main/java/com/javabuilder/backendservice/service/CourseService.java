package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;

import java.util.List;

public interface CourseService {

    ApiResponse<CreateCourseResponse> createCourse(CreateCourseRequest request);

    ApiResponse<CourseDetailResponse> findById(Long id);

    ApiResponse<List<CourseDetailResponse>> getCourses();

    void deleteById(Long id);

    ApiResponse<CourseDetailResponse> updateCourseById(Long id, UpdateCourseRequest request);

    ApiResponse<List<CourseDetailResponse>> searchCourse(String keyword);
}
