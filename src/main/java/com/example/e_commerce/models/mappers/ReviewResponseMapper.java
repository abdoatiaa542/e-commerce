package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ReviewResponseDto;
import com.example.e_commerce.models.entity.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewResponseMapper {
    Review toEntity(ReviewResponseDto reviewResponseDto);

    @Mapping(source = "id.productId", target = "productId")
//    @Mapping(source = "reviewDate", target = "reviewDate")
//    @Mapping(source = "reviewDescription", target = "reviewDescription")
//    @Mapping(source = "rating", target = "rating")
    ReviewResponseDto toDto(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ReviewResponseDto reviewResponseDto, @MappingTarget Review review);
}