package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.Nutrition;
import com.example.e_commerce.models.mappers.NutritionRequestDtoMapper;
import com.example.e_commerce.reposatory.NutritionRepository;
import com.example.e_commerce.service.utils.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionServiceImp implements NutritionService {

    @Autowired
    private NutritionRepository nutritionRepository;
    @Autowired
    private NutritionRequestDtoMapper nutritionRequestDtoMapper;

    @Override
    public List<Nutrition> getNutrition() {
        return nutritionRepository.findAll();
    }

    public Nutrition addNutrition(Nutrition nutrition) {
        return nutritionRepository.save(nutrition);
    }

    public Nutrition updateNutrition(Integer id, Nutrition nutrition) {
        Nutrition existingNutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutrition not found"));

        Nutrition nutrition1 = nutritionRequestDtoMapper
                .partialUpdate(nutritionRequestDtoMapper.toDto(nutrition), existingNutrition);
        return nutritionRepository.save(nutrition1);
    }


    public void deleteNutrition(int id) {
        nutritionRepository.deleteById(id);
    }


}















