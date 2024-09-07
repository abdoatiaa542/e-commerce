package com.example.e_commerce.service.impl;


import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.service.utils.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FavoritesServiceImp implements FavoritesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public Set<Product> getFavorites(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteProducts();
    }


    public Set<Product> addFavorite(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        user.getFavoriteProducts().add(product);
        userRepository.save(user);

        return user.getFavoriteProducts();
    }

    public Set<Product> removeFavorite(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        user.getFavoriteProducts().remove(product);
        userRepository.save(user);

        return user.getFavoriteProducts();
    }


}
