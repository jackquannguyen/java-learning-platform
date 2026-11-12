package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface JwtService {
    String generateToken(Long userId, Set<Role> role);
}
