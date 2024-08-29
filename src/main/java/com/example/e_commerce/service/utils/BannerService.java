package com.example.e_commerce.service.utils;

import com.example.e_commerce.models.entity.Banner;

import java.util.List;



public interface BannerService {

    public Banner addBanner(Banner banner);

    public Banner deleteBanner(Integer id);

    public List<Banner> getBanners();

}

