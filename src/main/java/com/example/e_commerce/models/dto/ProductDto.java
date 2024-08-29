package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Product}
 */

@Value
public class ProductDto implements Serializable {
    Integer id;
    String name;
    Short quantityInStock;
    String description;
    BigDecimal unitPrice;
    String imageUrl;
    String productDetails;
    BigDecimal discountPercentage;
    BigDecimal rate;

}