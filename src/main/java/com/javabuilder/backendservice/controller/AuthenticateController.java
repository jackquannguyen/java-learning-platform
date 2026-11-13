package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.request.AuthenticateRequest;
import com.javabuilder.backendservice.dto.request.RegisterUserRequest;
import com.javabuilder.backendservice.dto.response.AuthenticateResponse;
import com.javabuilder.backendservice.dto.response.RegisterUserResponse;
import com.javabuilder.backendservice.service.AuthenticateService;
import com.javabuilder.backendservice.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticateResponse authenticate(@RequestBody @Valid AuthenticateRequest request) throws JOSEException {
        return authenticateService.authenticate(request);
    }

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@RequestBody @Valid RegisterUserRequest request) {
        return userService.registerUser(request);
    }
}
