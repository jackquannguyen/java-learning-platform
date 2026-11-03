package com.javabuilder.backendservice.dto.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserResponse {
    private Long id;
    private String email;
    private String displayName;
    private Instant createdAt;
}
