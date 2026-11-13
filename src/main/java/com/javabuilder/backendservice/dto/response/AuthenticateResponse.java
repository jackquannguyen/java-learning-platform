package com.javabuilder.backendservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateResponse {
    private String accessToken;
    private String refreshToken;
}
