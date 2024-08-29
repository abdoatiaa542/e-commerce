package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.ReviewId}
 */
@Value
public class ReviewIdDto implements Serializable {
    Integer userId;
    Integer productId;
}