package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;

import java.util.List;

public interface ChapterService {

    CreateChapterResponse createChapter(Long courseId, CreateChapterRequest request);
    List<ChapterDetailResponse> getAllChapterDetails(Long courseId);
    ChapterDetailResponse getChapterById(Long courseId, Long chapterId);
    void deleteChapterById(Long courseId, Long chapterId);
    ChapterDetailResponse updateChapterById(Long courseId, Long chapterId, UpdateChapterRequest request);
}
