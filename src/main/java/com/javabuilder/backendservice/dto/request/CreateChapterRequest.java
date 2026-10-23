package com.javabuilder.backendservice.dto.request;

import lombok.Getter;

@Getter
public class CreateChapterRequest {
    private String name;
    private String description;
    private Integer orderIndex;
}
