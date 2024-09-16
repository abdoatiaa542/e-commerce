package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("SELECT p FROM Product p WHERE " + "LOWER(p.name) LIKE LOWER(CONCAT('%', :text, '%')) OR " + "LOWER(p.description) LIKE LOWER(CONCAT('%', :text, '%'))")
    Page<Product> searchByText(@Param("text") String text, Pageable pageable);


    @Procedure(procedureName = "get_bestselling")
    List<Product> getBestSellingProducts();
}