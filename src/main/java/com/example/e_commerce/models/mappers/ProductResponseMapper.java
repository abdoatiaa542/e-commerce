package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ProductResponseDto;
import com.example.e_commerce.models.entity.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductResponseMapper {
    Product toEntity(ProductResponseDto productResponseDto);

//    @Mapping(source = "category", target = "category.id")
//    @Mapping(source = "category", target = "category.name")
//    @Mapping(source = "category", target = "category.imageUrl")
//
//    @Mapping(source = "nutrition", target = "nutrition.id")
//    @Mapping(source = "nutrition", target = "nutrition.carbohydrates")
//    @Mapping(source = "nutrition", target = "nutrition.calories")
//    @Mapping(source = "nutrition", target = "nutrition.protein")
    ProductResponseDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductResponseDto productResponseDto, @MappingTarget Product product);

    Set<ProductResponseDto> toProductDtoSet(Set<Product> products);
    List<ProductResponseDto> toProductDtoList(List<Product> products);
}