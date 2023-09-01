package com.example.demo_jwt.controllers;

import com.example.demo_jwt.dtos.ApiResponse;
import com.example.demo_jwt.dtos.UserProfileResponse;
import com.example.demo_jwt.pojos.CreateUserRequest;
import com.example.demo_jwt.pojos.LoginUserRequest;
import com.example.demo_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userApi")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.signUp(createUserRequest);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserRequest loginUserRequest) {
        return userService.login(loginUserRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile() {
        ApiResponse<UserProfileResponse> apiResponse = userService.getUserProfile();
        return ResponseEntity.ok(apiResponse);
    }
}
