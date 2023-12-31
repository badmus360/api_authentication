package com.example.demo_jwt.service;

import com.example.demo_jwt.dtos.ApiResponse;
import com.example.demo_jwt.dtos.UserProfileResponse;
import com.example.demo_jwt.dtos.ViewAllUserResponse;
import com.example.demo_jwt.pojos.CreateUserRequest;
import com.example.demo_jwt.pojos.LoginUserRequest;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<ApiResponse> signUp(CreateUserRequest createUserRequest);
    ResponseEntity login(LoginUserRequest loginUserRequest);
    ApiResponse<UserProfileResponse> getAdminProfile();

    ViewAllUserResponse getAllUsers();
}