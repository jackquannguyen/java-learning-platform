package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.AuthenticateRequest;
import com.javabuilder.backendservice.dto.response.AuthenticateResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticateService {
    AuthenticateResponse authenticate(AuthenticateRequest request);
}
