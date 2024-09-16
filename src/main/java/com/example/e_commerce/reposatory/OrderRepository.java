package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    Optional<List<Order>> findByUserId(int userId);
}