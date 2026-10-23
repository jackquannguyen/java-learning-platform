package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateCourseRequest;
import com.javabuilder.backendservice.dto.request.UpdateCourseRequest;
import com.javabuilder.backendservice.dto.response.CourseResponse;
import com.javabuilder.backendservice.dto.response.CreateCourseResponse;
import com.javabuilder.backendservice.entity.Course;
import java.util.List;

public interface CourseService {

    CreateCourseResponse createCourse(CreateCourseRequest course);

    CourseResponse findById(Long id);

    List<CourseResponse> getCourses();

    void deleteById(Long id);

    CourseResponse updateCourseById(Long id, UpdateCourseRequest updateRequest);

    List<CourseResponse> searchCourse(String keyword);
}
