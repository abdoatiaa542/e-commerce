package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.CategoryDtoRequest;
import com.example.e_commerce.models.entity.Category;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryRequestMapper {

    Category toEntity(CategoryDtoRequest categoryDtoRequest);

    CategoryDtoRequest toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDtoRequest categoryDtoRequest, @MappingTarget Category category);
}