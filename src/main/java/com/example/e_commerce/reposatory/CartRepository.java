package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(Integer userId);

}