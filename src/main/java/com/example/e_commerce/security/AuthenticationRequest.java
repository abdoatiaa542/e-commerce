package com.example.e_commerce.security;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {

    private String email;
    private String password;



}