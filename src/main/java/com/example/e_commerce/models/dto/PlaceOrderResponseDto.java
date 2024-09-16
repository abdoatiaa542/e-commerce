package com.example.e_commerce.models.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PlaceOrderResponseDto implements Serializable {

    OrderDto order;
    String paymentLink;

}
