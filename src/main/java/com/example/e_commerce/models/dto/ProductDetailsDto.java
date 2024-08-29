package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.Product;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link Product}
 */
@Value
public class ProductDetailsDto implements Serializable {
    Integer id;
    String name;
    Short quantityInStock;
    String description;
    BigDecimal unitPrice;
    String imageUrl;
    CategoryDto category;
    String productDetails;
    BigDecimal discountPercentage;
    BigDecimal rate;
    NutritionDto nutrition;
    Set<ReviewDto> reviews;
}