package com.example.demo_jwt.pojos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoginUserRequest {
    @Email(message = "Email must not be null")
    private String email;
    @NotNull(message = "Password must not be null")
    private String password;
}
