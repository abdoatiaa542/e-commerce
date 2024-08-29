package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}