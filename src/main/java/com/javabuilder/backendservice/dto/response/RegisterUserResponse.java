package com.javabuilder.backendservice.dto.response;

import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.UserHasRole;
import lombok.*;

import java.time.Instant;
import java.util.Set;

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
    private Set<String> role;
}
