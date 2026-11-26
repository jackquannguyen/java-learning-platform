package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.RegisterUserRequest;
import com.javabuilder.backendservice.dto.response.ApiResponse;
import com.javabuilder.backendservice.dto.response.RegisterUserResponse;
import com.javabuilder.backendservice.dto.response.UserProfileInfo;

import java.util.List;

public interface UserService {
    ApiResponse<RegisterUserResponse> registerUser(RegisterUserRequest request);

    ApiResponse<UserProfileInfo> getUserProfileInfo();

    ApiResponse<List<UserProfileInfo>> getAllUsers();
}
