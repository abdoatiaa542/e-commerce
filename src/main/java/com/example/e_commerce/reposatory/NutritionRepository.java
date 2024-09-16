package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<Nutrition, Integer> {

}