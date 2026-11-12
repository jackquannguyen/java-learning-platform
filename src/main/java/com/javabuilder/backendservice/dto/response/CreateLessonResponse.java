package com.javabuilder.backendservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class CreateLessonResponse {
    private Long chapterId;
    private Long lessonId;
    private String title;
    private String videoUrl;
    private Boolean isFreePreview;
    private int orderIndex;
    private Instant createdAt;
}
