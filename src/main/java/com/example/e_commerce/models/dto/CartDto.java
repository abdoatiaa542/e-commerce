package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Cart}
 */


@Value
public class CartDto implements Serializable {
    Integer id;
    Set<CartItemDto> items;
}