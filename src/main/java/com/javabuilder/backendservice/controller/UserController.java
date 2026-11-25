package com.javabuilder.backendservice.controller;

import com.javabuilder.backendservice.dto.response.UserProfileInfo;
import com.javabuilder.backendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/myInfo")
    public UserProfileInfo getUserInfo() {
        return userService.getUserProfileInfo();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserProfileInfo> getAllUsers() {
        return userService.getAllUsers();
    }
}
