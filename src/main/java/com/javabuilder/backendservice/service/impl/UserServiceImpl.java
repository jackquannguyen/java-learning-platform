package com.javabuilder.backendservice.service.impl;

import com.javabuilder.backendservice.common.UserStatus;
import com.javabuilder.backendservice.dto.request.RegisterUserRequest;
import com.javabuilder.backendservice.dto.response.RegisterUserResponse;
import com.javabuilder.backendservice.entity.Role;
import com.javabuilder.backendservice.entity.User;
import com.javabuilder.backendservice.entity.UserHasRole;
import com.javabuilder.backendservice.repository.RoleRepository;
import com.javabuilder.backendservice.repository.UserRepository;
import com.javabuilder.backendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        boolean emailExist = userRepository.existsByEmail(request.getEmail());
        if (emailExist) {
            throw new IllegalArgumentException("Email already exists");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .displayName(request.getDisplayName())
                .createdAt(Instant.now())
                .userStatus(UserStatus.ACTIVE)
                .build();

        //Add Default "USER" Role
        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new IllegalStateException("Role not found!"));
        UserHasRole userHasRoles = UserHasRole.builder()
                .user(user)
                .role(role)
                .build();
        user.getUserHasRoles().add(userHasRoles);

        userRepository.save(user);

        return RegisterUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .createdAt(user.getCreatedAt())
                .role(user.getUserHasRoles().stream()
                        .map(userHasRole -> userHasRole.getRole().getName())
                        .collect(Collectors.toSet()))
                .build();
    }

}


