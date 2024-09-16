package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.Banner;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Banner}
 */
@Value
public class BannerRequestDto implements Serializable {
    
    String image;
    String description;
    LocalDate startTime;
    LocalDate endDate;

}