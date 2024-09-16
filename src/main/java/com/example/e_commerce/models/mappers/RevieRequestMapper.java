package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ReviewRequestDto;
import com.example.e_commerce.models.entity.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RevieRequestMapper {

    Review toEntity(ReviewRequestDto reviewRequestDto);

    @Mapping(source = "id.productId", target = "productId")
    ReviewRequestDto toDto(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ReviewRequestDto reviewRequestDto, @MappingTarget Review review);
}