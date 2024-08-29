package com.example.e_commerce.models.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.e_commerce.models.entity.User}
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    String userName;
    String email;
    String password;
    String address;
    String phoneNumber;
    String imageUrl;
    Boolean isDeleted;
}