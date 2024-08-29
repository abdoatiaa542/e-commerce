package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}