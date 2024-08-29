package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.NutritionDto;
import com.example.e_commerce.models.entity.Nutrition;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NutritionMapper {
    Nutrition toEntity(NutritionDto nutritionDto);

    NutritionDto toDto(Nutrition nutrition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Nutrition partialUpdate(NutritionDto nutritionDto, @MappingTarget Nutrition nutrition);
}