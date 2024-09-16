package com.example.e_commerce.service.impl;


import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.security.OTPGenerator;
import com.example.e_commerce.service.utils.OTPService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class OTPServiceImp implements OTPService {

    @Autowired
    private UserRepository userRepository;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;


    //    private static final String TEXT_BELT_API = "https://textbelt.com/text";
//    private static final String TEXT_BELT_API_KEY = "textbelt"; // Free tier key
    private final Map<String, OTPInfo> otpStorage = new ConcurrentHashMap<>();


    public void sendOTP(String phoneNumber) {
        String otp = OTPGenerator.generateOTP();
        otpStorage.put(phoneNumber, new OTPInfo(otp, LocalDateTime.now()));
        sendSMS(phoneNumber, "Your OTP is: " + otp);
    }

//    private void sendSMS(String phoneNumber, String message) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> request = new HashMap<>();
//        request.put("phone", phoneNumber);
//        request.put("message", message);
//        request.put("key", TEXT_BELT_API_KEY);
//
//        Map<String, Object> response = restTemplate.postForObject(TEXT_BELT_API, request, Map.class);
//        boolean success = (boolean) response.get("success");
//        if (!success) {
//            throw new RuntimeException("Failed to send SMS: " + response.get("error"));
//        }
//    }

    public boolean verifyOTP(String phoneNumber, String otp) {
        OTPInfo otpInfo = otpStorage.get(phoneNumber);
        if (otpInfo == null || !otpInfo.getOtp().equals(otp)) {
            return false;
        }

        if (otpInfo.getGeneratedTime().plusMinutes(5).isBefore(LocalDateTime.now())) {
            otpStorage.remove(phoneNumber);
            return false; // OTP expired
        }

        otpStorage.remove(phoneNumber);
        return true;
    }

    private static class OTPInfo {
        private final String otp;
        private final LocalDateTime generatedTime;

        public OTPInfo(String otp, LocalDateTime generatedTime) {
            this.otp = otp;
            this.generatedTime = generatedTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getGeneratedTime() {
            return generatedTime;
        }
    }


    private void sendSMS(String phoneNumber, String message) {
        try {
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    message
            ).create();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SMS: " + e.getMessage());
        }
    }


}
