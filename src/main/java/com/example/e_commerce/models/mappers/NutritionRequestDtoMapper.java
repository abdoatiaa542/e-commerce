package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.NutritionRequestDto;
import com.example.e_commerce.models.dto.NutritionResponseDto;
import com.example.e_commerce.models.entity.Nutrition;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NutritionRequestDtoMapper {


    // تحويل من NutritionRequestDto إلى Nutrition entity
    Nutrition toEntity(NutritionRequestDto dto);

    // تحويل من Nutrition entity إلى NutritionRequestDto
    NutritionRequestDto toDto(Nutrition entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Nutrition partialUpdate(NutritionRequestDto nutritionResponseDto, @MappingTarget Nutrition nutrition);

}