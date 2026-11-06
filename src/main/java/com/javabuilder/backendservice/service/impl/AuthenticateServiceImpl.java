package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.dto.request.AuthenticateRequest;
import com.javabuilder.backendservice.dto.response.AuthenticateResponse;
import com.javabuilder.backendservice.entity.User;
import com.javabuilder.backendservice.repository.UserRepository;
import com.javabuilder.backendservice.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(request.getEmail(), request.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        User user = (User) authenticationResponse.getPrincipal();

        return AuthenticateResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .build();
    }
}
