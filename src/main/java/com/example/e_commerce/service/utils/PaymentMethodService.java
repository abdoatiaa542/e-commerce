package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.PaymentMethod;

public interface PaymentMethodService {

    public PaymentMethod getPaymentMethodByName(String name);
}
