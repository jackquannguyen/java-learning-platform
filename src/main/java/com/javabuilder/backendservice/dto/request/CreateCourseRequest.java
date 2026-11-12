package com.javabuilder.backendservice.dto.request;

import com.javabuilder.backendservice.common.CourseLevel;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateCourseRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String thumbnail;
    private CourseLevel courseLevel;
}
