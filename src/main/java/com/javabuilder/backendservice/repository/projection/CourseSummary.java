package com.javabuilder.backendservice.repository.projection;

import java.math.BigDecimal;

public interface CourseSummary {
    String getName();
    String getDescription();
    BigDecimal getPrice();
    String getThumbnail();
}
