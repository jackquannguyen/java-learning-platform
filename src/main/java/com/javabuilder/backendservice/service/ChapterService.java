package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import org.springframework.web.accept.ApiVersionResolver;

import java.util.List;

public interface ChapterService {

    ApiResponse<CreateChapterResponse> createChapter(Long courseId, CreateChapterRequest request);

    ApiResponse<List<ChapterDetailResponse>> getAllChapterDetails(Long courseId);

    ApiResponse<ChapterDetailResponse> getChapterById(Long chapterId);

    void deleteChapterById(Long chapterId);

    ApiResponse<ChapterDetailResponse> updateChapterById(
            Long chapterId,
            UpdateChapterRequest request);
}
