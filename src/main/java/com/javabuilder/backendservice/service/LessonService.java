package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateLessonRequest;
import com.javabuilder.backendservice.dto.request.UpdateLessonRequest;
import com.javabuilder.backendservice.dto.response.CreateLessonResponse;
import com.javabuilder.backendservice.dto.response.LessonDetailResponse;

import java.util.List;

public interface LessonService {
    CreateLessonResponse createLesson(Long chapterId, CreateLessonRequest request);
    List<LessonDetailResponse> getAllLessonDetails(Long chapterId);
    LessonDetailResponse getLessonById(Long lessonId);
    void deleteLessonById (Long lessonId);
    LessonDetailResponse updateLessonById(Long lessonId, UpdateLessonRequest request);
}
