package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{courseId}")
    public ApiResponse<CreateChapterResponse> createChapter(@PathVariable Long courseId,
                                                            @RequestBody CreateChapterRequest request) {
        return chapterService.createChapter(courseId, request);
    }

    @GetMapping("/course/{courseId}")
    public ApiResponse<List<ChapterDetailResponse>> getAllChapter(@PathVariable Long courseId) {
        return chapterService.getAllChapterDetails(courseId);
    }

    @GetMapping("/{chapterId}")
    public ApiResponse<ChapterDetailResponse> getChapter(@PathVariable Long chapterId) {
        return chapterService.getChapterById(chapterId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{chapterId}")
    public void deleteChapter(@PathVariable Long chapterId) {
        chapterService.deleteChapterById(chapterId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{chapterId}")
    public ApiResponse<ChapterDetailResponse> updateChapter(@PathVariable Long chapterId,
                                                            @RequestBody UpdateChapterRequest request) {
        return chapterService.updateChapterById(chapterId, request);
    }
}
