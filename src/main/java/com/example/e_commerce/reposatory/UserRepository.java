package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}