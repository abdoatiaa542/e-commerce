package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.Banner;
import com.example.e_commerce.reposatory.BannerRepository;
import com.example.e_commerce.service.utils.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BannerServiceImp implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;


    private final String uploadUrl = "https://api.imgur.com/3/image";
    private final String clientId = "529f42521284438";


    @Override
    public Banner addBanner(Banner banner) {
        String imageUrl = uploadImageToExternalService(banner.getImage());
        banner.setImage(imageUrl);
        return bannerRepository.save(banner);
    }


    @Override
    public List<Banner> getBanners() {
        return bannerRepository.findAll();
    }


    @Override
    public Banner deleteBanner(Integer bannerId) {
        Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new ResourceNotFoundException("Banner not found"));

        bannerRepository.delete(banner);
        return banner;
    }


    String uploadImageToExternalService(String base64Image) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Client-ID " + clientId);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("image", base64Image);
        requestBody.put("type", "base64");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, request, Map.class);
            System.out.println("Response status code: " + response.getStatusCodeValue());
            System.out.println("Response body: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody != null && responseBody.containsKey("Data")) {
                    Map<String, Object> data = (Map<String, Object>) responseBody.get("Data");
                    if (data != null && data.containsKey("Link")) {
                        return (String) data.get("Link");
                    } else {
                        System.err.println("Response does not contain 'Link' in 'Data'");
                        return "Image uploaded successfully, but link not found in response";
                    }
                } else {
                    System.err.println("Response does not contain 'Data' key");
                    return "Image uploaded successfully, but unexpected response format";
                }
            } else {
                throw new RuntimeException("Failed to upload image. Status code: " + response.getStatusCodeValue());
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error response body: " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image due to unexpected error", e);
        }
    }

}

