package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.ReviewIdDto;
import com.example.e_commerce.models.entity.ReviewId;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewIdMapper {
    ReviewId toEntity(ReviewIdDto reviewIdDto);

    ReviewIdDto toDto(ReviewId reviewId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ReviewId partialUpdate(ReviewIdDto reviewIdDto, @MappingTarget ReviewId reviewId);
}