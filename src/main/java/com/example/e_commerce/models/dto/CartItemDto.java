package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.CartItem}
 */


@Value
public class CartItemDto implements Serializable {
    ProductResponseDto product;
    Long quantity;
}