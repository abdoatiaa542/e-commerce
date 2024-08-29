package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.UserRole}
 */
@Value
public class UserRoleDto implements Serializable {
    Integer id;
    String role;
}