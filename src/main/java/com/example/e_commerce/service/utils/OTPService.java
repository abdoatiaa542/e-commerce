package com.example.e_commerce.service.utils;


public interface OTPService {


    void sendOTP(String phoneNumber);

    boolean verifyOTP(String phoneNumber, String otp);

}
