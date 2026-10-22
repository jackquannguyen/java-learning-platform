package com.javabuilder.backendservice.repository;

import com.javabuilder.backendservice.dto.CourseSummary;
import com.javabuilder.backendservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("Select c from Course c where lower(c.name) like lower(concat('%', :keyword, '%')) or lower(c.description) like lower(concat('%', :keyword, '%'))")
    List<CourseSummary> findByNameOrDescription(String keyword);

//    List<CourseSummary> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
//            String name,
//            String description
//    );
}
