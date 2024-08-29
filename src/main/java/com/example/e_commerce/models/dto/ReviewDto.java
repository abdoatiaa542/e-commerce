package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Review}
 */
@Value
public class ReviewDto implements Serializable {
    ReviewIdDto id;
    BigDecimal rating;
    Instant reviewDate;
    String reviewDescription;
}