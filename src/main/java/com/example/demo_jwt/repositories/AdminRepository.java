package com.example.demo_jwt.repositories;

import com.example.demo_jwt.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Users, Long> {

//    List<Users> findByRole_RoleUserContainingOrderById(Users users);
}
