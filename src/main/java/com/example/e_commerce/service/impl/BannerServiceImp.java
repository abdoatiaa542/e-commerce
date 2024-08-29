package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.Banner;
import com.example.e_commerce.reposatory.BannerRepository;
import com.example.e_commerce.service.utils.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class BannerServiceImp implements BannerService {


    @Autowired
    private BannerRepository bannerRepository;

//    @Autowired
//    BannerMapper bannerMapper;

    private final String uploadUrl = "https://api.imgur.com/3/image";
    private final String deleteUrl = "https://api.imgur.com/3/image/{{imageDeleteHash}}";


    private final String clientId = "529f42521284438"; // استبدلها بالمفتاح الخاص بك


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


    private String uploadImageToExternalService(String base64Image) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON); // headers  >>>> Content-Type: application/json
        headers.set("Authorization", "Client-ID " + clientId);  // headers  >>> Authorization: Client-ID 529f42521284438

        Map<String, String> requestBody = Map.of(    // body
                "image", base64Image,
                "type", "base64",
                "title", "My Image",
                "description", "My Image Description"
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);  //
        ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
            return (String) data.get("link");
        } else {
            throw new RuntimeException("Failed to upload image");
        }

    }


    @Override
    public Banner deleteBanner(Integer bannerId) {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner not found"));

        bannerRepository.delete(banner);
        return banner;
    }


//    private void deleteImageById(String imageId) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Client-ID " + clientId);
//
//        HttpEntity<Void> request = new HttpEntity<>(headers);
//
//        String deleteUrl = uploadUrl + "/" + imageId;
//
//        try {
//            // إرسال الطلب باستخدام exchange()
//            restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Void.class);
//        } catch (RestClientException e) {
//            // تسجيل الرسالة اللي حصل فيها الاستثناء
//            System.err.println("Error deleting image with ID: " + imageId + ". Exception: " + e.getMessage());
//            // رمي استثناء جديد مع معلومات إضافية
//            throw new RuntimeException("Failed to delete image from external service with ID: " + imageId, e);
//        }
//    }
//

//    private String extractImageId(String imageUrl) {
//        // التحقق من أن الـ imageUrl ليس فارغاً أو null
//        if (imageUrl == null || imageUrl.isEmpty()) {
//            throw new IllegalArgumentException("Invalid image URL");
//        }
//
//        // استخراج الـ imageId من الـ URL (بحسب تنسيق الـ URL المستلم)
//        String imageId = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
//        System.out.println(imageId);
//        return imageId;
//    }


}
