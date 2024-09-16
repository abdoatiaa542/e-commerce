package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Nutrition;

import java.util.List;

public interface NutritionService {


    List<Nutrition> getNutrition();

    Nutrition addNutrition(Nutrition nutrition);

    Nutrition updateNutrition(Integer id, Nutrition nutrition);

    void deleteNutrition(int id);
}
