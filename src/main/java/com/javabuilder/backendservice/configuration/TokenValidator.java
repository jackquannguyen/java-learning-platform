package com.javabuilder.backendservice.configuration;

import com.javabuilder.backendservice.common.TokenStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
@RequiredArgsConstructor
public class TokenValidator implements OAuth2TokenValidator<Jwt> {
    @Override
    @NonNull
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if (TokenStatus.ACCESS_TOKEN != TokenStatus.valueOf(jwt.getClaimAsString("typ"))) {
            OAuth2Error oauth2Error = new OAuth2Error("invalid_token");
            return OAuth2TokenValidatorResult.failure(oauth2Error);
        }
        return OAuth2TokenValidatorResult.success();
    }
}


