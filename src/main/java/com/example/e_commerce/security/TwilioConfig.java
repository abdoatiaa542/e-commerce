package com.example.e_commerce.security;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private   String ACCOUNT_SID ;
    @Value("${twilio.auth.token}")
    private   String AUTH_TOKEN ;

    @Bean
    public TwilioConfig twilioInit() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        return new TwilioConfig();
    }
}