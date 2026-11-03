package com.javabuilder.backendservice.mapper;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Course;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ChapterMapper {
    public Chapter toChapter(Course course, CreateChapterRequest request) {
        return Chapter.builder()
                .name(request.getName())
                .description(request.getDescription())
                .orderIndex(request.getOrderIndex())
                .createdAt(Instant.now())
                .course(course)
                .build();
    }

    public CreateChapterResponse toCreateChapterResponse(Course course, Chapter chapter) {
        return CreateChapterResponse.builder()
                .courseId(course.getId())
                .chapterId(chapter.getId())
                .chapterName(chapter.getName())
                .chapterDescription(chapter.getDescription())
                .chapterOrderIndex(chapter.getOrderIndex())
                .createdAt(chapter.getCreatedAt())
                .build();
    }

    public ChapterDetailResponse toChapterDetailResponse(Chapter chapter) {
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
