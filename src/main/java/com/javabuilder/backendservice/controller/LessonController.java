package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.request.CreateLessonRequest;
import com.javabuilder.backendservice.dto.request.UpdateLessonRequest;
import com.javabuilder.backendservice.dto.response.CreateLessonResponse;
import com.javabuilder.backendservice.dto.response.LessonDetailResponse;
import com.javabuilder.backendservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{chapterId}")
    public CreateLessonResponse createLesson(
            @PathVariable Long chapterId,
            @RequestBody CreateLessonRequest request) {
        return lessonService.createLesson(chapterId, request);
    }

    @GetMapping("/chapter/{chapterId}")
    public List<LessonDetailResponse> getAllLessons(@PathVariable Long chapterId) {
        return lessonService.getAllLessonDetails(chapterId);
    }

    @GetMapping("/{lessonId}")
    public LessonDetailResponse getLesson(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLessonById(lessonId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{lessonId}")
    public LessonDetailResponse updateLesson(
            @PathVariable Long lessonId,
            @RequestBody UpdateLessonRequest request) {
        return lessonService.updateLessonById(lessonId, request);
    }

}
