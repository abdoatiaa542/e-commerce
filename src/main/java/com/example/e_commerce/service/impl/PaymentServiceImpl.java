package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.NotPaidException;
import com.example.e_commerce.service.utils.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


//OkHttpClient client = new OkHttpClient().newBuilder()
//        .build();
//MediaType mediaType = MediaType.parse("application/json");
//RequestBody body = RequestBody.create(mediaType, "{
//        \"username\": \"{{username}}\",
//        \"password\": \"{{password}}\"
//}");
//Request request = new Request.Builder()
//        .url("https://accept.paymob.com/api/auth/tokens")
//        .method("POST", body)
//        .addHeader("Content-Type", "application/json")
//        .build();
//Response response = client.newCall(request).execute();


@Service
public class PaymentServiceImpl implements PaymentService {


    private final RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${paymob.api.key}")
    private String apiKey;

    @Value("${paymob.integration.id}")
    private String integrationId;


    // لإنشاء رابط دفع
    public String createPaymentLink(double price) {
        String token = getAuthToken();
        return getPaymentLink(token, String.valueOf(price * 100));
    }


    // للحصول على التوكن
    private String getAuthToken() {

        String url = "https://accept.paymob.com/api/auth/tokens";


        // إعداد الهيدرز
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // إعداد الـ body
        Map<String, String> body = new HashMap<>();
        body.put("api_key", apiKey);

        // تجهيز الطلب
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        // تنفيذ الطلب والحصول على الاستجابة
//       System.err.println("++++++++++++++++++++++++");
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//        System.err.println("++++++++++++++++++++++++");

        Map<String, Object> responseBody;

        try {
            responseBody = objectMapper.readValue(response.getBody(), Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Payment link generation failed");
        }

        if (responseBody.containsKey("token")) {
            return responseBody.get("token").toString();
        } else {
            throw new RuntimeException("Authentication failed");  //
        }

    }


    // لتوليد رابط الدفع
    private String getPaymentLink(String token, String priceInCents) {
        String url = "https://accept.paymob.com/api/ecommerce/payment-links";

        // إعداد الهيدرز
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // إعداد الـ body
        Map<String, Object> body = new HashMap<>();
        body.put("amount_cents", priceInCents);
        body.put("payment_methods", Collections.singletonList(integrationId));
        body.put("is_live", "false");

        // تجهيز الطلب
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // تنفيذ الطلب والحصول على الاستجابة
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        Map<String, Object> responseBody;
        try {
            responseBody = objectMapper.readValue(response.getBody(), Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Payment link generation failed");
        }

        if (responseBody.containsKey("client_url")) {
            return responseBody.get("client_url").toString();
        } else {
            throw new RuntimeException("Payment link generation failed");
        }
    }


    @Override
    public boolean isPaymentDone(String paymentLink) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(paymentLink, HttpMethod.GET, entity, String.class);

        Map<String, Object> responseBody;
        try {
            responseBody = objectMapper.readValue(response.getBody(), Map.class);
        } catch (Exception e) {
            throw new NotPaidException("Not paid");
        }


        if (responseBody.containsKey("paid")) {
            return (Boolean) responseBody.get("paid");
        } else {
            throw new NotPaidException("Not paid");
        }
    }

}
