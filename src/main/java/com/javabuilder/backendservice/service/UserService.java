package com.javabuilder.backendservice.service;

import com.javabuilder.backendservice.dto.request.RegisterUserRequest;
import com.javabuilder.backendservice.dto.response.RegisterUserResponse;
import com.javabuilder.backendservice.dto.response.UserProfileInfo;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    UserProfileInfo getUserProfileInfo();
}
