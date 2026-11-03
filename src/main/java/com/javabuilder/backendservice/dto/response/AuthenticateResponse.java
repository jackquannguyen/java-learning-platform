package com.javabuilder.backendservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateResponse {
    private Long id;
    private String email;
    private String displayName;
}
