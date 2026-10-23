package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.entity.Course;
import java.util.List;

public interface CourseService {

    CreateCourseResponse createCourse(CreateCourseRequest request);

    CourseDetailResponse findById(Long id);

    List<CourseDetailResponse> getCourses();

    void deleteById(Long id);

    CourseDetailResponse updateCourseById(Long id, UpdateCourseRequest request);

    List<CourseDetailResponse> searchCourse(String keyword);
}
