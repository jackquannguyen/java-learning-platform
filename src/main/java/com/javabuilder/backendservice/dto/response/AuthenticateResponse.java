package com.javabuilder.backendservice.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateResponse {
    private String accessToken;
    private String refreshToken;
    private Set<String> roles;
}
