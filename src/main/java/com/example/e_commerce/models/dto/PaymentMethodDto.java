package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.PaymentMethod;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link PaymentMethod}
 */

@Value
public class PaymentMethodDto implements Serializable {
    Integer id;
    String methodName;
}