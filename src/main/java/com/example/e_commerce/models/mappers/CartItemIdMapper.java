package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.CartItemIdDto;
import com.example.e_commerce.models.entity.CartItemId;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemIdMapper {
    CartItemId toEntity(CartItemIdDto cartItemIdDto);

    CartItemIdDto toDto(CartItemId cartItemId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItemId partialUpdate(CartItemIdDto cartItemIdDto, @MappingTarget CartItemId cartItemId);
}