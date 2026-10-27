package com.javabuilder.backendservice.dto.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDetailResponse {
    private Long id;
    private String name;
    private String description;
    private Integer orderIndex;
    private Instant createdAt;
    private Instant updatedAt;
}
