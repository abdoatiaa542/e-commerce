package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ProductResponseDto;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.mappers.ProductResponseMapper;
import com.example.e_commerce.security.JwtUtil;
import com.example.e_commerce.service.utils.FavoritesService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private ProductResponseMapper productmapper;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("")
    public ResponseEntity<Set<ProductResponseDto>> getFavorites(HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        Set<Product> productDto = favoritesService.getFavorites(userId);
        return ResponseEntity.ok(productmapper.toProductDtoSet(productDto));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDto> addFavorite(HttpServletRequest request, @RequestBody JsonNode body) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        int productId = body.get("productId").asInt();
        Product updatedFavorites = favoritesService.addFavorite(userId, productId);
        return ResponseEntity.ok(productmapper.toDto(updatedFavorites));
    }


    @DeleteMapping("")
    public ResponseEntity<ProductResponseDto> removeFavorite(HttpServletRequest request, @RequestBody JsonNode body) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        int productId = body.get("productId").asInt();
        Product updatedFavorites = favoritesService.removeFavorite(userId, productId);
        return ResponseEntity.ok(productmapper.toDto(updatedFavorites));
    }


}












