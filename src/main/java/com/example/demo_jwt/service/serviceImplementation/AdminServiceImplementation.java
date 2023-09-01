package com.example.demo_jwt.service.serviceImplementation;

import com.example.demo_jwt.service.AdminService;
import com.example.demo_jwt.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AppUtil appUtil;

//    @Override
//    public ViewAllUserResponse getAllUsers() {
//
//        Users user = appUtil.getLoggedInUser();
//
//        ViewAllUserResponse response = ViewAllUserResponse.builder()
//
////                .firstName(user.getFirstName())
////                .lastName(user.getLastName())
////                .email(user.getEmail())
//                .build();
//        return null;
//    }


}
