package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}