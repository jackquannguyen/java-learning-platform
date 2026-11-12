package com.javabuilder.backendservice.repository;

import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("select ch from Chapter ch where ch.course.id = :courseId")
    List<Chapter> findAllById(Long courseId);

    @Query("select ch from Chapter ch where ch.id = :chapterId")
    Chapter findChapterById(Long chapterId);

    @Modifying
    @Transactional
    @Query("delete from Chapter ch where ch.id = :chapterId")
    boolean deleteChapterById(Long chapterId);
}