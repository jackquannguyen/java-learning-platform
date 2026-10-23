package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Course;
import com.javabuilder.backendservice.repository.ChapterRepository;
import com.javabuilder.backendservice.repository.CourseRepository;
import com.javabuilder.backendservice.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    @Override
    public CreateChapterResponse createChapter(Long courseId, CreateChapterRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // 1. Convert request to chapter entity
        Chapter chapter = Chapter.builder()
                .name(request.getName())
                .description(request.getDescription())
                .orderIndex(request.getOrderIndex())
                .createdAt(Instant.now())
                .course(course)
                .build();

        // 2. Save chapter entity to database

        chapterRepository.save(chapter);

        // 3. Convert chapter entity to response
        return CreateChapterResponse.builder()
                .courseId(course.getId())
                .chapterId(chapter.getId())
                .chapterName(chapter.getName())
                .chapterDescription(chapter.getDescription())
                .chapterOrderIndex(chapter.getOrderIndex())
                .createdAt(chapter.getCreatedAt())
                .build();
    }

}
