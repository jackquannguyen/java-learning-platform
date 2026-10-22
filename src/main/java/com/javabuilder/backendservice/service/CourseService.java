package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.entity.Course;
import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    Course findById(Long id);

    List<Course> getCourses();

    void deleteById(Long id);

    Course updateCourseById(Long id, Course newCourse);

    List<Course> searchCourse(String keyword);
}
