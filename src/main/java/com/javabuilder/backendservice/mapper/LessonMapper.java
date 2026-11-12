package com.javabuilder.backendservice.mapper;

import com.javabuilder.backendservice.dto.request.CreateLessonRequest;
import com.javabuilder.backendservice.dto.response.CreateLessonResponse;
import com.javabuilder.backendservice.dto.response.LessonDetailResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toLesson(Chapter chapter,CreateLessonRequest request) {
        return Lesson.builder()
                .title(request.getTitle())
                .videoUrl(request.getVideoUrl())
                .isFreePreview(request.getIsFreePreview())
                .orderIndex(request.getOrderIndex())
                .chapter(chapter)
                .build();
    }

    public CreateLessonResponse toCreateLessonResponse(Lesson lesson) {
        return CreateLessonResponse.builder()
                .chapterId(lesson.getChapter().getId())
                .lessonId(lesson.getId())
                .title(lesson.getTitle())
                .videoUrl(lesson.getVideoUrl())
                .isFreePreview(lesson.getIsFreePreview())
                .orderIndex(lesson.getOrderIndex())
                .createdAt(lesson.getCreatedAt())
                .build();
    }

    public LessonDetailResponse toLessonDetailResponse(Lesson lesson) {
        return LessonDetailResponse.builder()
                .lessonId(lesson.getId())
                .title(lesson.getTitle())
                .videoUrl(lesson.getVideoUrl())
                .isFreePreview(lesson.getIsFreePreview())
                .orderIndex(lesson.getOrderIndex())
                .createdAt(lesson.getCreatedAt())
                .updatedAt(lesson.getUpdatedAt())
                .build();
    }
}
