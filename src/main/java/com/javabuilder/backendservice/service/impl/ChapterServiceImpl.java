package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.CreateChapterRequest;
import com.javabuilder.backendservice.dto.request.UpdateChapterRequest;
import com.javabuilder.backendservice.dto.response.ChapterDetailResponse;
import com.javabuilder.backendservice.dto.response.CreateChapterResponse;
import com.javabuilder.backendservice.entity.Chapter;
import com.javabuilder.backendservice.entity.Course;
import com.javabuilder.backendservice.mapper.ChapterMapper;
import com.javabuilder.backendservice.repository.ChapterRepository;
import com.javabuilder.backendservice.repository.CourseRepository;
import com.javabuilder.backendservice.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;
    private final ChapterMapper chapterMapper;

    @Override
    public CreateChapterResponse createChapter(Long courseId, CreateChapterRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // 1. Convert request to chapter entity
        Chapter chapter = chapterMapper.toChapter(course, request);

        // 2. Save chapter entity to database
        chapterRepository.save(chapter);

        // 3. Convert chapter entity to response
        return chapterMapper.toCreateChapterResponse(course, chapter);
    }

    @Override
    public List<ChapterDetailResponse> getAllChapterDetails(Long courseId) {
        List<Chapter> chapterList = chapterRepository.findAllById(courseId);
        return chapterList.stream().map(chapterMapper::toChapterDetailResponse).toList();
    }

    @Override
    public ChapterDetailResponse getChapterById(Long chapterId) {
        Chapter chapter = chapterRepository.findChapterById(chapterId);
        return chapterMapper.toChapterDetailResponse(chapter);
        }

    @Override
    public void deleteChapterById(Long chapterId) {
         boolean res = chapterRepository.deleteChapterById(chapterId);
         if (!res) {
             throw new IllegalArgumentException("Chapter not found");
         }
    }

    @Override
    public ChapterDetailResponse updateChapterById(Long chapterId, UpdateChapterRequest request) {
        Chapter chapter = chapterRepository.findChapterById(chapterId);
        if (chapter == null) {
            throw new IllegalArgumentException("Chapter not found");
        }
        chapter.setName(request.getName());
        chapter.setDescription(request.getDescription());
        chapter.setOrderIndex(request.getOrderIndex());
        chapter.setUpdatedAt(Instant.now());

        chapterRepository.save(chapter);
        return chapterMapper.toChapterDetailResponse(chapter);
    }
}
