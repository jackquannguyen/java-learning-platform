package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Course;
import com.javabuilder.backendservice.repository.ChapterRepository;
import com.javabuilder.backendservice.repository.CourseRepository;
import com.javabuilder.backendservice.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    @Override
    public List<ChapterDetailResponse> getAllChapterDetails(Long courseId) {
        List<Chapter> chapterList = chapterRepository.findAllById(courseId);

        return chapterList.stream().map(chapter -> ChapterDetailResponse.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .description(chapter.getDescription())
                .orderIndex(chapter.getOrderIndex())
                .createdAt(chapter.getCreatedAt())
                .updatedAt(chapter.getUpdatedAt())
                .build()
        ).toList();
    }

    @Override
    public ChapterDetailResponse getChapterById(Long courseId, Long chapterId) {
        Chapter chapter = chapterRepository.findByIdAndCourseId(chapterId, courseId);

        return ChapterDetailResponse.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .description(chapter.getDescription())
                .orderIndex(chapter.getOrderIndex())
                .createdAt(chapter.getCreatedAt())
                .updatedAt(chapter.getUpdatedAt())
                .build();
        }

    @Override
    public void deleteChapterById(Long courseId, Long chapterId) {
         boolean res = chapterRepository.deleteByIdAndCourseId(courseId, chapterId);
         if (!res) {
             throw new IllegalArgumentException("Chapter not found");
         }
    }

    @Override
    public ChapterDetailResponse updateChapterById(Long courseId, Long chapterId, UpdateChapterRequest request) {
        Chapter chapter = chapterRepository.findByIdAndCourseId(chapterId, courseId);
        if (chapter == null) {
            throw new IllegalArgumentException("Chapter not found");
        }
        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrderIndex(request.getOrderIndex());
        chapter.setUpdatedAt(Instant.now());

        chapterRepository.save(chapter);
        return ChapterDetailResponse.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .description(chapter.getDescription())
                .orderIndex(chapter.getOrderIndex())
                .createdAt(chapter.getCreatedAt())
                .updatedAt(chapter.getUpdatedAt())
                .build();
    }
}
