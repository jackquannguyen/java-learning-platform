package com.javabuilder.backendservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthenticateRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid Email format")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min=3, message = "Password muss have at least 3 characters")
    String password;
}
