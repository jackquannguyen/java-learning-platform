package com.javabuilder.backendservice.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateCourseRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String thumbnail;
}
