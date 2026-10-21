package com.javabuilder.backendservice.dto;

import java.math.BigDecimal;

public interface CourseSummary {
    String getName();
    String getDescription();
    BigDecimal getPrice();
    String getThumbnail();
}
