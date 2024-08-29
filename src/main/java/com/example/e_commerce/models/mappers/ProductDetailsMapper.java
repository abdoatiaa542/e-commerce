package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ProductDetailsDto;
import com.example.e_commerce.models.entity.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductDetailsMapper {
    Product toEntity(ProductDetailsDto productDetailsDto);

    @AfterMapping
    default void linkReviews(@MappingTarget Product product) {
        product.getReviews().forEach(review -> review.setProduct(product));
    }

    ProductDetailsDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDetailsDto productDetailsDto, @MappingTarget Product product);
}