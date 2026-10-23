package com.javabuilder.backendservice.repository.projection;

import java.math.BigDecimal;
import java.time.Instant;

public interface CourseSummary {
    String getName();
    String getDescription();
    BigDecimal getPrice();
    String getThumbnail();
    Instant getCreatedAt();
    Instant getUpdatedAt();
}
