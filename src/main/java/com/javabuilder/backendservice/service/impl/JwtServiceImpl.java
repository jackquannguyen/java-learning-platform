package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.common.TokenStatus;
import com.javabuilder.backendservice.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    public String generateAccessToken(Long userId, Set<String> roles) {
        // 1. Header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. Payload
        Date issueTimeVar = new Date();
        Date expirationTimeVar = Date.from(issueTimeVar
                .toInstant()
                .plus(30, ChronoUnit.MINUTES));
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userId))
                .issueTime(issueTimeVar)
                .expirationTime(expirationTimeVar)
                .claim("typ", TokenStatus.ACCESS_TOKEN)
                .claim("role", roles)
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
    public String generateRefreshToken(Long userId) throws JOSEException {
        // 1. Header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. Payload
        Date issueTimeVar = new Date();
        Date expirationTimeVar = Date.from(issueTimeVar
                .toInstant()
                .plus(30, ChronoUnit.DAYS));
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userId))
                .issueTime(issueTimeVar)
                .expirationTime(expirationTimeVar)
                .claim("typ", TokenStatus.REFRESH_TOKEN)
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
