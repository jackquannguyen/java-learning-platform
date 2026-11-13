package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

import java.util.Set;

public interface JwtService {
    String generateAccessToken(Long userId, Set<Role> role) throws JOSEException;
    String generateRefreshToken(Long userId, Set<Role> role) throws JOSEException;
}
