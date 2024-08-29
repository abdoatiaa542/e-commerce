package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.OrderItemId}
 */
@Value
public class OrderItemIdDto implements Serializable {
    Integer orderId;
    Integer productId;
}