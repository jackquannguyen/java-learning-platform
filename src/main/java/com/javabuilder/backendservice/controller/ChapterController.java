package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/{courseId}")
    public CreateChapterResponse createChapter(@PathVariable Long courseId, @RequestBody CreateChapterRequest request) {
        return chapterService.createChapter(courseId, request);
    }

    /*
    1. Viết API Lấy ra danh sách chương học theo courseId
    2. Delete 1 chương học
    3. Update 1 chương học
    4. Tìm hiểu về Spring Validation
    5. Tìm hiểu về ApiResponse chuẩn trong Restful API -> Cách chuẩn hóa API trong hệ thống Restful API
    6. Tìm hiểu về cách tích hợp Swagger vào ứng dụng Spring Boot
     */

    @GetMapping("/{courseId}")
    public List<ChapterDetailResponse> getAllChapter(@PathVariable Long courseId) {
        return chapterService.getAllChapterDetails(courseId);
    }

    @GetMapping("/{courseId}/{chapterId}")
    public ChapterDetailResponse getChapter(@PathVariable Long chapterId) {
        return chapterService.getChapterById(chapterId);
    }

    @DeleteMapping("/{courseId}/{chapterId}")
    public void deleteChapter(@PathVariable Long chapterId){
        chapterService.deleteChapterById(chapterId);
    }

    @PutMapping("/{courseId}/{chapterId}")
    public ChapterDetailResponse updateChapter(@PathVariable Long chapterId, @RequestBody UpdateChapterRequest request) {
        return chapterService.updateChapterById(chapterId, request);
    }
}
