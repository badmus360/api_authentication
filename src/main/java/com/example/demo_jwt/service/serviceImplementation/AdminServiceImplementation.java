package com.example.demo_jwt.service.serviceImplementation;

import com.example.demo_jwt.configurations.security.CustomUserDetailsService;
import com.example.demo_jwt.configurations.security.JwtUtil;
import com.example.demo_jwt.dtos.ApiResponse;
import com.example.demo_jwt.dtos.UserProfileResponse;
import com.example.demo_jwt.dtos.ViewAllUserResponse;
import com.example.demo_jwt.enums.Role;
import com.example.demo_jwt.models.Users;
import com.example.demo_jwt.pojos.CreateUserRequest;
import com.example.demo_jwt.pojos.LoginUserRequest;
import com.example.demo_jwt.repositories.AdminRepository;
import com.example.demo_jwt.repositories.UserRepository;
import com.example.demo_jwt.service.AdminService;
import com.example.demo_jwt.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AppUtil appUtil;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    public final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Override
    public ResponseEntity<ApiResponse> signUp(CreateUserRequest createUserRequest) {
        Boolean isUserExist = adminRepository.existsByEmail(createUserRequest.getEmail());
        if (isUserExist)
            throw new ValidationException("User Already Exists!");

        Users admin = Users.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .confirmationToken(jwtUtil.generateToken(createUserRequest.getEmail()))
                .role(Role.ROLE_ADMIN)
                .build();

        adminRepository.save(admin);

        return ResponseEntity.ok(new ApiResponse("successful", "signup successfully...", null));
    }

    @Override
    public ResponseEntity login(LoginUserRequest loginUserRequest) {
        Boolean isAdminExist = adminRepository.existsByEmail(loginUserRequest.getEmail());
        if (!isAdminExist)
            throw new ValidationException("Admin does not Exists!");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));

        UserDetails user = customUserDetailsService.loadUserByUsername(loginUserRequest.getEmail());

        if (user != null)
            return ResponseEntity.ok(jwtUtil.generateToken(loginUserRequest.getEmail()));

        return ResponseEntity.status(400).body("some error just occured");
    }

    @Override
    public ApiResponse<UserProfileResponse> getAdminProfile() {
        Users admin = appUtil.getLoggedInUser();

        UserProfileResponse response = UserProfileResponse.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .build();

        return new ApiResponse<>("Success", "User Profile", response);
    }

    @Override
    public ViewAllUserResponse getAllUsers() {

        Users user = appUtil.getLoggedInUser();
        List<Users> users = userRepository.findAllById(Collections.singleton(user.getId()));

        ViewAllUserResponse response = ViewAllUserResponse.builder()
                .listOfUsers(users)
                .build();
        return  response;
    }


}
