package com.example.e_commerce.service.impl;


import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.PaymentMethod;
import com.example.e_commerce.reposatory.PaymentMethodRepository;
import com.example.e_commerce.service.utils.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class paymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod getPaymentMethodByName(String paymentMethodName) {

        return paymentMethodRepository.findByMethodName(paymentMethodName)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMethod not found"));
    }

}
