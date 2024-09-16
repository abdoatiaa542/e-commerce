package com.example.e_commerce.models.dto;

import lombok.Value;

import java.math.BigDecimal;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Product}
 */

@Value
public class ProductRequestDto {

    String name;
    Short quantityInStock;
    String description;
    BigDecimal unitPrice;
    String imageUrl;
    String productDetails;
    BigDecimal discountPercentage; //  ممكن ابعته ووممكن لا


    Integer categoryId;
    Integer nutritionId; //  ممكن ابعته ووممكن لا


}
