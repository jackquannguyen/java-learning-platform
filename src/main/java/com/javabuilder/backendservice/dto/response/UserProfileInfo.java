package com.javabuilder.backendservice.dto.response;

import com.javabuilder.backendservice.common.UserStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileInfo {
    private Long id;
    private String email;
    private String displayName;
    private String avatarUrl;
    private Instant dateOfBirth;
    private Instant createdAt;
    private UserStatus userStatus;
}
