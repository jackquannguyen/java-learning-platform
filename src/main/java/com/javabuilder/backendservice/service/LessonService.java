package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateLessonRequest;
import com.javabuilder.backendservice.dto.request.UpdateLessonRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.CreateLessonResponse;
import com.javabuilder.backendservice.dto.response.LessonDetailResponse;

import java.util.List;

public interface LessonService {
    ApiResponse<CreateLessonResponse> createLesson(Long chapterId, CreateLessonRequest request);

    ApiResponse<List<LessonDetailResponse>> getAllLessonDetails(Long chapterId);

    ApiResponse<LessonDetailResponse> getLessonById(Long lessonId);

    void deleteLessonById(Long lessonId);

    ApiResponse<LessonDetailResponse> updateLessonById(Long lessonId, UpdateLessonRequest request);
}
