package com.example.demo_jwt.repositories;

import com.example.demo_jwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);

}
