package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Nutrition}
 */
@Value
public class NutritionResponseDto implements Serializable {
    Integer id;
    Integer calories;
    Integer protein;
    Integer carbohydrates;
}