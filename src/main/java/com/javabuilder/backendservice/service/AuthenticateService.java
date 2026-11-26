package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.AuthenticateRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.AuthenticateResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticateService {
    ApiResponse<AuthenticateResponse> authenticate(AuthenticateRequest request) throws JOSEException;
}
