package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.CartItemId}
 */
@Value
public class CartItemIdDto implements Serializable {
    Integer cartId;
    Integer productId;
}