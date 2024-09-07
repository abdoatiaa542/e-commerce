package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    String username;
    String email;
    String password;
    String address;;
    String phoneNumber;
    String imageUrl;
    Boolean isDeleted;
}