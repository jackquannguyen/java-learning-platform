package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.AuthenticateRequest;
import com.javabuilder.backendservice.dto.response.AuthenticateResponse;
import com.javabuilder.backendservice.entity.User;
import com.javabuilder.backendservice.repository.RoleRepository;
import com.javabuilder.backendservice.repository.UserRepository;
import com.javabuilder.backendservice.service.AuthenticateService;
import com.javabuilder.backendservice.service.JwtService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        User user = (User) authenticate.getPrincipal();

        return AuthenticateResponse.builder()
                .token(jwtService.generateToken(user.getId(), Set.of()))
                .build();
    }
}
