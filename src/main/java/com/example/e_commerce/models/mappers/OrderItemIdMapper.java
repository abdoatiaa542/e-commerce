package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.OrderItemIdDto;
import com.example.e_commerce.models.entity.OrderItemId;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemIdMapper {
    OrderItemId toEntity(OrderItemIdDto orderItemIdDto);

    OrderItemIdDto toDto(OrderItemId orderItemId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItemId partialUpdate(OrderItemIdDto orderItemIdDto, @MappingTarget OrderItemId orderItemId);
}