package com.javabuilder.backendservice.dto.request;

import lombok.Getter;

@Getter
public class UpdateLessonRequest {
    private String title;
    private String videoUrl;
    private Boolean isFreePreview;
    private int orderIndex;
}
