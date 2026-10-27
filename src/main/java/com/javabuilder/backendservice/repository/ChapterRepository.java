package com.javabuilder.backendservice.repository;

import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("select ch from Chapter ch where ch.course.id = :courseId")
    List<Chapter> findAllById(Long courseId);

    Chapter findByIdAndCourseId(Long chapterId, Long courseId);

    @Modifying
    @Transactional
    @Query("delete from Chapter ch where ch.course.id = :courseId and ch.id = :chapterId")
    boolean deleteByIdAndCourseId(Long courseId, Long chapterId);

}

