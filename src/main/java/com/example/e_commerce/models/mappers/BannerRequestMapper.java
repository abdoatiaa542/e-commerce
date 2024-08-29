package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.BannerRequestDto;
import com.example.e_commerce.models.entity.Banner;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BannerRequestMapper {
    Banner toEntity(BannerRequestDto bannerRequestDto);

    BannerRequestDto toDto(Banner banner);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Banner partialUpdate(BannerRequestDto bannerRequestDto, @MappingTarget Banner banner);
}