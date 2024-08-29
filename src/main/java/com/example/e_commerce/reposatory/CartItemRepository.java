package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.CartItem;
import com.example.e_commerce.models.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}