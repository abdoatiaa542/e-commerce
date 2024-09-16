package com.example.e_commerce.models.dto;


import lombok.Value;

import java.math.BigDecimal;


@Value
public class ReviewRequestDto {

    private Integer productId;
    private BigDecimal rating;
    private String reviewDescription;


}