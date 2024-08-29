package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.e_commerce.models.entity.Banner}
 */
@Value
public class BannerResponseDto implements Serializable {
    Integer id;
    String image;
    LocalDate startTime;
    LocalDate endDate;
    String description;

}