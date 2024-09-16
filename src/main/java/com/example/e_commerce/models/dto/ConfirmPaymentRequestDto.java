package com.example.e_commerce.models.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmPaymentRequestDto implements java.io.Serializable {
    String paymentLink;
    Integer orderId;
}
