package com.javabuilder.backendservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class CreateChapterResponse {
    private Long courseId;
    private Long chapterId;
    private String chapterName;
    private String chapterDescription;
    private Integer chapterOrderIndex;
    private Instant createdAt;
}
