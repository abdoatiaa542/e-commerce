package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    OrderItemIdDto id;
    Integer quantity;
}