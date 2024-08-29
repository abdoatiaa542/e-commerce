package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.PaymentMethodDto;
import com.example.e_commerce.models.entity.PaymentMethod;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMethodMapper {
    PaymentMethod toEntity(PaymentMethodDto paymentMethodDto);

    PaymentMethodDto toDto(PaymentMethod paymentMethod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentMethod partialUpdate(PaymentMethodDto paymentMethodDto, @MappingTarget PaymentMethod paymentMethod);
}