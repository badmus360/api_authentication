package com.example.demo_jwt.service.serviceImplementation;

import com.example.demo_jwt.configurations.security.CustomUserDetailsService;
import com.example.demo_jwt.configurations.security.JwtUtil;
import com.example.demo_jwt.dtos.ApiResponse;
import com.example.demo_jwt.dtos.UserProfileResponse;
import com.example.demo_jwt.enums.Role;
import com.example.demo_jwt.models.Users;
import com.example.demo_jwt.pojos.CreateUserRequest;
import com.example.demo_jwt.pojos.LoginUserRequest;
import com.example.demo_jwt.repositories.UserRepository;
import com.example.demo_jwt.service.UserService;
import com.example.demo_jwt.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    public final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AppUtil appUtil;

    @Override
    public ResponseEntity<ApiResponse> signUp(CreateUserRequest createUserRequest) {

        Boolean isUserExist = userRepository.existsByEmail(createUserRequest.getEmail());
        if (isUserExist)
            throw new ValidationException("User Already Exists!");

        Users user = Users.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .confirmationToken(jwtUtil.generateToken(createUserRequest.getEmail()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse("successful", "signup successfully...", null));
    }

    @Override
    public ResponseEntity login(LoginUserRequest loginUserRequest) {

        Boolean isUserExist = userRepository.existsByEmail(loginUserRequest.getEmail());
        if (!isUserExist)
            throw new ValidationException("User does not Exists!");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));

        UserDetails user = customUserDetailsService.loadUserByUsername(loginUserRequest.getEmail());

        if (user != null)
            return ResponseEntity.ok(jwtUtil.generateToken(loginUserRequest.getEmail()));

        return ResponseEntity.status(400).body("some error just occured");
    }

    @Override
    public ApiResponse<UserProfileResponse> getUserProfile() {
        Users user = appUtil.getLoggedInUser();

        UserProfileResponse response = UserProfileResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return new ApiResponse<>("Success", "User Profile", response);
}

}
