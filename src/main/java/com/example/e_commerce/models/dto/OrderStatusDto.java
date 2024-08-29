package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.OrderStatus;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link OrderStatus}
 */
@Value
public class OrderStatusDto implements Serializable {
    Integer id;
    String name;
}