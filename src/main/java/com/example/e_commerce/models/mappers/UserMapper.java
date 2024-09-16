package com.example.e_commerce.models.mappers;

import com.example.e_commerce.models.dto.UserDto;
import com.example.e_commerce.models.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

//    @Mapping(target = "role.id", source = "roleId")
    User toEntity(UserDto userDto);

//    @Mapping(target = "roleId", source = "role.id")
    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}