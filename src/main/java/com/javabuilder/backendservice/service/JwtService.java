package com.javabuilder.backendservice.service;

import com.nimbusds.jose.JOSEException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;


public interface JwtService {
    String generateAccessToken(Long userId, Set<String> roles) throws JOSEException;

    String generateRefreshToken(Long userId) throws JOSEException;
}
