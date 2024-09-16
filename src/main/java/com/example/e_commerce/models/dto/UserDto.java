package com.example.e_commerce.models.dto;

import com.example.e_commerce.models.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    //    Integer id;          //

    String username;
    String email;
    String password;
    String address;
    String phoneNumber;
    String imageUrl;

//    Boolean isDeleted;   //
//    Integer roleId;      //

}