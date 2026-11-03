package com.javabuilder.backendservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterUserRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email (message = "Invalid Email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3, message = "Password muss have at least 3 characters")
    private String password;

    @NotBlank(message = "Display name cannot be blank")
    private String displayName;
}
