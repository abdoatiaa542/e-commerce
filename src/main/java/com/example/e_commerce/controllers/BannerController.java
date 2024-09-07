package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.BannerRequestDto;
import com.example.e_commerce.models.dto.BannerResponseDto;
import com.example.e_commerce.models.entity.Banner;
import com.example.e_commerce.models.mappers.BannerRequestMapper;
import com.example.e_commerce.models.mappers.BannerResponseMapper;
import com.example.e_commerce.service.utils.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerRequestMapper bannerRequestMapper;
    @Autowired
    private BannerResponseMapper bannerResponseMapper;


    @PostMapping("/add")
    public ResponseEntity<BannerResponseDto> addBanner(@RequestBody BannerRequestDto bannerRequestDto) {
        Banner banner = bannerRequestMapper.toEntity(bannerRequestDto);
        Banner savedBanner = bannerService.addBanner(banner);
        return ResponseEntity.ok(bannerResponseMapper.toDto(savedBanner));
    }


    @GetMapping("/get_all")
    public ResponseEntity<List<BannerResponseDto>> getBanners() {
        List<Banner> banners = bannerService.getBanners();
        return ResponseEntity.ok(bannerResponseMapper.toDto(banners));
    }


    // return object
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BannerResponseDto> deleteBanner(@PathVariable Integer id) {
        Banner banner = bannerService.deleteBanner(id);
        return ResponseEntity.ok(bannerResponseMapper.toDto(banner));
    }

}