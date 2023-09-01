package com.example.demo_jwt.dtos;

import com.example.demo_jwt.models.Users;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewAllUserResponse extends BaseResponse {
    private List<Users> listOfUsers;
}
