package com.javabuilder.backendservice.dto.response;

import com.javabuilder.backendservice.common.CourseLevel;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String thumbnail;
    private Instant createdAt;
    private Instant updatedAt;
    private CourseLevel courseLevel;
}
