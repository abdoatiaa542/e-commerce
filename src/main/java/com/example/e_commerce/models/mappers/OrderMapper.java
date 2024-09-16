package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.OrderDto;
import com.example.e_commerce.models.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

//    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
    }

    @Mapping(source = "status.name" , target="status")
    @Mapping(source = "paymentMethod.methodName" , target="paymentMethod")
    OrderDto toDto(Order order);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);


    List<OrderDto> toOrderDtoList(List<Order> orders);
}