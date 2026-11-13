package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.User;
import com.javabuilder.backendservice.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    public String generateAccessToken(Long userId, Set<Role> role){
        // 1. Header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. Payload
        Date issueTimeVar = new Date();
        Date expirationTimeVar = Date.from(issueTimeVar.toInstant().plus(30, ChronoUnit.MINUTES));
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userId))
                .issueTime(issueTimeVar)
                .expirationTime(expirationTimeVar)
                .claim("typ", "access")
                .build();

        // 3. Signature
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), payload);

        try {
            JWSSigner signer = new MACSigner(secretKey);
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return signedJWT.serialize();
    }

    @Override
    public String generateRefreshToken(Long userId, Set<Role> role) throws JOSEException {
        // 1. Header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. Payload
        Date issueTimeVar = new Date();
        Date expirationTimeVar = Date.from(issueTimeVar.toInstant().plus(30, ChronoUnit.DAYS));
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userId))
                .issueTime(issueTimeVar)
                .expirationTime(expirationTimeVar)
                .claim("typ", "refresh")
                .build();

        // 3. Signature
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
