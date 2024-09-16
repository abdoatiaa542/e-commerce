package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.CategoryResponseDto;
import com.example.e_commerce.models.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryResponseMapper {
    Category toEntity(CategoryResponseDto categoryResponseDto);

    CategoryResponseDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryResponseDto categoryResponseDto, @MappingTarget Category category);

    List<CategoryResponseDto> toDtoList(List<Category> categories);
}