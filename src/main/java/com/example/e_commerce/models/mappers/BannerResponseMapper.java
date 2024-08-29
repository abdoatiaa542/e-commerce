package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.BannerResponseDto;
import com.example.e_commerce.models.entity.Banner;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BannerResponseMapper {
    Banner toEntity(BannerResponseDto bannerResponseDto);

    BannerResponseDto toDto(Banner banner);

    // return list of dto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<BannerResponseDto> toDto(List<Banner> banners);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Banner partialUpdate(BannerResponseDto bannerResponseDto, @MappingTarget Banner banner);
}