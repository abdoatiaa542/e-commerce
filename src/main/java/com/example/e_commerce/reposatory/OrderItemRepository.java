package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.OrderItem;
import com.example.e_commerce.models.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}