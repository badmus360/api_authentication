package com.example.demo_jwt.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private  String firstName;
    private  String lastName;
    private  String email;
}
