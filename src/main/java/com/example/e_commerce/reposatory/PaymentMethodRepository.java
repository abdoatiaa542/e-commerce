package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {


    @Query("SELECT p FROM PaymentMethod p WHERE LOWER(p.methodName) = LOWER(:name)")
    Optional<PaymentMethod> findByMethodName(@Param("name") String name);

}