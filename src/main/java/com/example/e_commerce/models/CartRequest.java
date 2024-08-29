package com.example.e_commerce.models;


import org.springframework.web.bind.annotation.RequestBody;

public class CartRequest {

    public int userId;
    public int productId;
    public int quantity;

}
