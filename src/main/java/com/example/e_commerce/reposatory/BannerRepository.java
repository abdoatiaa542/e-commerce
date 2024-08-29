package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
}