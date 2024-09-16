package com.example.e_commerce.service.utils;


public interface PaymentService {


    public String createPaymentLink(double price);

    public boolean isPaymentDone(String paymentLink);

}
