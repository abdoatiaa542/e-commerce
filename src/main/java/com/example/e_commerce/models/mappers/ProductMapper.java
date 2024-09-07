package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ProductDto;
import com.example.e_commerce.models.entity.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);

    List<ProductDto> toProductDtoList(List<Product> products);
    Set<ProductDto> toProductDtoSet(Set<Product> products);

}