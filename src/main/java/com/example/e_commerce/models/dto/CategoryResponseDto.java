package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.Category;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryResponseDto implements Serializable {
    Integer id;
    String name;
    String imageUrl;
}