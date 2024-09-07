package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Nutrition;

public interface NutritionService {


    Nutrition getNutritionById(Integer id);

    Nutrition addNutrition(Nutrition nutrition);

    Nutrition updateNutrition(Integer id, Nutrition nutrition);

    void deleteNutrition(int id);
}
