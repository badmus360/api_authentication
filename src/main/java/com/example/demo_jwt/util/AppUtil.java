package com.example.demo_jwt.util;

import com.example.demo_jwt.exceptions.UserNotFoundException;
import com.example.demo_jwt.models.Users;
import com.example.demo_jwt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUtil {

    private final UserRepository userRepository;

    public Users getLoggedInUser() throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(((UserDetails)principal).getUsername())
                .orElseThrow(() -> new UserNotFoundException("Error getting logged in user"));
    }


}
