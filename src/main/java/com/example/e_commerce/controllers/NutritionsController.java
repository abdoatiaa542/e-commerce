package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.NutritionRequestDto;
import com.example.e_commerce.models.dto.NutritionResponseDto;
import com.example.e_commerce.models.entity.Nutrition;
import com.example.e_commerce.models.mappers.NutritionRequestDtoMapper;
import com.example.e_commerce.models.mappers.NutritionResponseMapper;
import com.example.e_commerce.service.utils.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/nutritions")
public class NutritionsController {

    @Autowired
    private NutritionService nutritionService;
    @Autowired
    private NutritionRequestDtoMapper nutritionRequestDtoMapper;
    @Autowired
    private NutritionResponseMapper nutritionResponseDtoMapper;


    @GetMapping("")
    public ResponseEntity<List<NutritionResponseDto>> getNutrition() {
        List<Nutrition> nutrition = nutritionService.getNutrition();
        return ResponseEntity.ok(nutritionResponseDtoMapper.toNutritionResponseDtoSet(nutrition));
    }

    @PostMapping("/add")
    public ResponseEntity<NutritionResponseDto> addNutrition(@RequestBody NutritionRequestDto nutritionRequestDto) {
        Nutrition nutrition = nutritionRequestDtoMapper.toEntity(nutritionRequestDto);
        Nutrition newNutrition = nutritionService.addNutrition(nutrition);
        return ResponseEntity.status(HttpStatus.CREATED).body(nutritionResponseDtoMapper.toDto(newNutrition));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NutritionResponseDto> updateNutrition(@PathVariable int id , @RequestBody NutritionRequestDto nutritionRequestDto) {
        Nutrition nutrition = nutritionRequestDtoMapper.toEntity(nutritionRequestDto);
        Nutrition updatedNutrition = nutritionService.updateNutrition(id, nutrition);
        return ResponseEntity.ok(nutritionResponseDtoMapper.toDto(updatedNutrition));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNutrition(@PathVariable int id) {
        nutritionService.deleteNutrition(id);
        return ResponseEntity.noContent().build();
    }

}
