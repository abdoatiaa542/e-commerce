package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ProductRequestDto;
import com.example.e_commerce.models.entity.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductRequestMapper {


    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "nutritionId", target = "nutrition.id")
    Product toEntity(ProductRequestDto productRequestDto);

    ProductRequestDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductRequestDto productRequestDto, @MappingTarget Product product);
}