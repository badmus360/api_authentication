package com.example.demo_jwt.repositories;

import com.example.demo_jwt.dtos.ViewAllUserResponse;
import com.example.demo_jwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);

}
