package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ReviewRequestUpdateDto;
import com.example.e_commerce.models.entity.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewRequestUpdateDtoMapper {

    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "reviewDescription", target = "reviewDescription")
    Review toEntity(ReviewRequestUpdateDto reviewRequestUpdateDto);

    ReviewRequestUpdateDto toDto(Review review);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    ReviewRequestUpdateDto partialUpdate(ReviewRequestUpdateDto reviewRequestUpdateDto, @MappingTarget Review review);
}