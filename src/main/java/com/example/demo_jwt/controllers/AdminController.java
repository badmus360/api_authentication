package com.example.demo_jwt.controllers;

import com.example.demo_jwt.dtos.ApiResponse;
import com.example.demo_jwt.dtos.UserProfileResponse;
import com.example.demo_jwt.dtos.ViewAllUserResponse;
import com.example.demo_jwt.pojos.CreateUserRequest;
import com.example.demo_jwt.pojos.LoginUserRequest;
import com.example.demo_jwt.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adminApi")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp(CreateUserRequest createUserRequest) {
        return adminService.signUp(createUserRequest);
    }
    @PostMapping("/login")
    public ResponseEntity login(LoginUserRequest loginUserRequest) {
        return adminService.login(loginUserRequest);
    }
    @GetMapping("/admin")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getAdminProfile() {
        ApiResponse<UserProfileResponse> apiResponse = adminService.getAdminProfile();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/allAdmin")
    public ViewAllUserResponse getAllUsers(){
        return adminService.getAllUsers();
    }

}
