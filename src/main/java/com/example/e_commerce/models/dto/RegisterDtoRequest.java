package com.example.e_commerce.models.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDtoRequest {

    private String username;
    private String email;
    private String password;

}
