package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.entity.Role;
import com.nimbusds.jose.JOSEException;
import java.util.Set;

public interface JwtService {
    String generateAccessToken(Long userId, Set<Role> role) throws JOSEException;
    String generateRefreshToken(Long userId, Set<Role> role) throws JOSEException;
}
