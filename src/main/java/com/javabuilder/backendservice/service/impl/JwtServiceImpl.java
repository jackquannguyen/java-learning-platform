package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.User;
import com.javabuilder.backendservice.repository.UserRepository;
import com.javabuilder.backendservice.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(Long userId, Set<Role> role) {
        // 1. Header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. Payload
        Date issueTimeVar = new Date();
        Date expirationTimeVar = Date.from(issueTimeVar.toInstant().plus(30, ChronoUnit.MINUTES));
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userId))
                .issueTime(issueTimeVar)
                .expirationTime(expirationTimeVar)
                .build();

        // 3. Signature
        String secretKey = "vVsyqUkzs8TT5/+hiz3u8xm6CYQjVzyDnmJtm333rHo=";
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), payload);

        try {
            JWSSigner signer = new MACSigner(secretKey);
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return signedJWT.serialize();
    }
}
