package com.example.demo_jwt.pojos;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateUserRequest {
    @NotBlank(message = "First name cannot be blank")
    private  String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private  String lastName;
    @Email(message = "Please enter a valid email address")
    private  String email;
    @NotBlank(message = "Password cannot be blank")
    private  String password;
}
