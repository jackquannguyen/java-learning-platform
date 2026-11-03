package com.javabuilder.backendservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateCourseRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String thumbnail;
}
