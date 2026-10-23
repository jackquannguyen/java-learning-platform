package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;

public interface ChapterService {

    CreateChapterResponse createChapter(Long courseId, CreateChapterRequest request);
}
