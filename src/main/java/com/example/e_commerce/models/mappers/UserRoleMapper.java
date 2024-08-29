package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.UserRoleDto;
import com.example.e_commerce.models.entity.UserRole;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper {
    UserRole toEntity(UserRoleDto userRoleDto);

    UserRoleDto toDto(UserRole userRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRole partialUpdate(UserRoleDto userRoleDto, @MappingTarget UserRole userRole);
}