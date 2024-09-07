package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.Nutrition;
import com.example.e_commerce.reposatory.NutritionRepository;
import com.example.e_commerce.service.utils.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceImp implements NutritionService {

    @Autowired
    private NutritionRepository nutritionRepository;

    @Override
    public Nutrition getNutritionById(Integer id) {
        return nutritionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrition not found"));
    }

    public Nutrition addNutrition(Nutrition nutrition) {
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(Integer id, Nutrition nutrition) {
        Nutrition existingNutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrition not found"));
        return nutritionRepository.save(nutrition);
    }

    public void deleteNutrition(int id) {
        nutritionRepository.deleteById(id);
    }


}















