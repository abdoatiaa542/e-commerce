package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Category}
 */
@Value
public class CategoryDtoRequest implements Serializable {
    String name;
    String imageUrl;
}