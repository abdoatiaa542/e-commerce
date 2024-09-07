package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.NutritionDto;
import com.example.e_commerce.models.entity.Nutrition;
import com.example.e_commerce.models.mappers.NutritionMapper;
import com.example.e_commerce.service.impl.NutritionServiceImp;
import com.example.e_commerce.service.utils.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class NutritionsController {

    @Autowired
    private NutritionService nutritionService;
    @Autowired
    private NutritionMapper nutritionMapper;


    @GetMapping("/{id}")
    public ResponseEntity<NutritionDto> getNutrition(@PathVariable int id) {
        Nutrition nutrition = nutritionService.getNutritionById(id);
        return ResponseEntity.ok(nutritionMapper.toDto(nutrition));
    }


    @PostMapping("/add")
    public ResponseEntity<NutritionDto> addNutrition(@RequestBody NutritionDto nutritionDto) {
        Nutrition nutrition = nutritionMapper.toEntity(nutritionDto);
        Nutrition newNutrition = nutritionService.addNutrition(nutrition);
        return ResponseEntity.status(HttpStatus.CREATED).body(nutritionMapper.toDto(newNutrition));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NutritionDto> updateNutrition(@PathVariable int id, @RequestBody NutritionDto nutritionDto) {
        Nutrition nutrition = nutritionMapper.toEntity(nutritionDto);
        Nutrition updatedNutrition = nutritionService.updateNutrition(id, nutrition);
        return ResponseEntity.ok(nutritionMapper.toDto(updatedNutrition));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNutrition(@PathVariable int id) {
        nutritionService.deleteNutrition(id);
        return ResponseEntity.noContent().build();
    }
}
