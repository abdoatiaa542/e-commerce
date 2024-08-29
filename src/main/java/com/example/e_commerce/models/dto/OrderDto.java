package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.Order;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Order}
 */
@Value
public class OrderDto implements Serializable {
    Integer id;
    Instant orderDate;
    Instant shippingDate;
    OrderStatusDto status;
    PaymentMethodDto paymentMethod;
    BigDecimal totalPrice;
    Set<OrderItemDto> orderItems;
}