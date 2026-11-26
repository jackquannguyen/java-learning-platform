package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateLessonRequest;
import com.javabuilder.backendservice.dto.request.UpdateLessonRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.CreateLessonResponse;
import com.javabuilder.backendservice.dto.response.LessonDetailResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Lesson;
import com.javabuilder.backendservice.mapper.LessonMapper;
import com.javabuilder.backendservice.repository.ChapterRepository;
import com.javabuilder.backendservice.repository.LessonRepository;
import com.javabuilder.backendservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final ChapterRepository chapterRepository;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public ApiResponse<CreateLessonResponse> createLesson(Long chapterId, CreateLessonRequest request) {
        Chapter chapter = chapterRepository
                .findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found!"));
        Lesson lesson = lessonMapper.toLesson(chapter, request);
        lessonRepository.save(lesson);
        return new ApiResponse<>("success", "Lesson create success", lessonMapper.toCreateLessonResponse(lesson));
    }

    @Override
    public ApiResponse<List<LessonDetailResponse>> getAllLessonDetails(Long chapterId) {
        Chapter chapter = chapterRepository
                .findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found!"));
        List<Lesson> lessonList = lessonRepository.findAllById(chapterId);
        return new ApiResponse<>("success", "Get All Lessons success", lessonList
                .stream()
                .map(lessonMapper::toLessonDetailResponse)
                .toList());
    }

    @Override
    public ApiResponse<LessonDetailResponse> getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found!"));
        return new ApiResponse<>("success", "Get Lesson success", lessonMapper.toLessonDetailResponse(lesson));
    }

    @Override
    public void deleteLessonById(Long lessonId) {
        Lesson deletedLesson = lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found!"));
        lessonRepository.delete(deletedLesson);
    }

    @Override
    public ApiResponse<LessonDetailResponse> updateLessonById(Long lessonId, UpdateLessonRequest request) {
        Lesson currentLesson = lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found!"));

        currentLesson.setTitle(request.getTitle());
        currentLesson.setVideoUrl(request.getVideoUrl());
        currentLesson.setIsFreePreview(request.getIsFreePreview());
        currentLesson.setOrderIndex(request.getOrderIndex());
        lessonRepository.save(currentLesson);

        return new ApiResponse<>("success", "Update Lesson success",
                lessonMapper.toLessonDetailResponse(currentLesson));
    }
}
