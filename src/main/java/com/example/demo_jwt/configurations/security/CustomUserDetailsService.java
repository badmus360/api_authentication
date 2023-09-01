package com.example.demo_jwt.configurations.security;

import com.example.demo_jwt.enums.Role;
import com.example.demo_jwt.models.Users;
import com.example.demo_jwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService  {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        return new User(users.getEmail(), users.getPassword(), getGrantedAuthorities(users.getRole() == null ? Role.ROLE_USER : users.getRole()));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Role roles) {
        return Collections
                .singleton(new SimpleGrantedAuthority(roles.toString()));
    }
}
