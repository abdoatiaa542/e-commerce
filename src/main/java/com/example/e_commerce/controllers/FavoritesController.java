package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ProductDto;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.mappers.ProductMapper;
import com.example.e_commerce.service.utils.FavoritesService;
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
    private ProductMapper productmapper;


    @GetMapping("/{userId}")
    public ResponseEntity<Set<ProductDto>> getFavorites(@PathVariable int userId) {
        Set<Product> productDto = favoritesService.getFavorites(userId);
        return ResponseEntity.ok(productmapper.toProductDtoSet(productDto));
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<Set<Product>> addFavorite(@PathVariable int userId, @PathVariable int productId) {
        Set<Product> updatedFavorites = favoritesService.addFavorite(userId, productId);
        return ResponseEntity.ok(updatedFavorites);
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<Set<Product>> removeFavorite(@PathVariable int userId, @PathVariable int productId) {
        Set<Product> updatedFavorites = favoritesService.removeFavorite(userId, productId);
        return ResponseEntity.ok(updatedFavorites);
    }


}












