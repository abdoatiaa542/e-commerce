package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.OrderStatusDto;
import com.example.e_commerce.models.entity.OrderStatus;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderStatusMapper {
    OrderStatus toEntity(OrderStatusDto orderStatusDto);

    OrderStatusDto toDto(OrderStatus orderStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderStatus partialUpdate(OrderStatusDto orderStatusDto, @MappingTarget OrderStatus orderStatus);
}