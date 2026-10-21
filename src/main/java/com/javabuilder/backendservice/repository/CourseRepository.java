package com.javabuilder.backendservice.repository;

import com.javabuilder.backendservice.dto.CourseSummary;
import com.javabuilder.backendservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<CourseSummary> findByNameContaining(String name);
}
