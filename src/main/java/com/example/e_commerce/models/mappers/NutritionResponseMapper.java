package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.NutritionResponseDto;
import com.example.e_commerce.models.entity.Nutrition;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NutritionResponseMapper {
    Nutrition toEntity(NutritionResponseDto nutritionResponseDto);

    NutritionResponseDto toDto(Nutrition nutrition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Nutrition partialUpdate(NutritionResponseDto nutritionResponseDto, @MappingTarget Nutrition nutrition);

    List<NutritionResponseDto> toNutritionResponseDtoSet(List<Nutrition> nutrition);

}