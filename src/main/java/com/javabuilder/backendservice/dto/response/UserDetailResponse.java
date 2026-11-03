package com.javabuilder.backendservice.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailResponse {
    private Long id;
    private String email;
    private String displayName;
    private String avatarUrl;
    private Instant dateOfBirth;
    private Instant createdAt;
}
