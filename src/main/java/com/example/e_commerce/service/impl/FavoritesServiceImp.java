package com.example.e_commerce.service.impl;


import com.example.e_commerce.exceptions.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user.getFavoriteProducts();
    }


    public Product addFavorite(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        user.getFavoriteProducts().add(product);
        userRepository.save(user);
        return product;

    }

    public Product removeFavorite(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product  not found"));

        boolean isDeleted = user.getFavoriteProducts().remove(product);
        if (!isDeleted) {
            throw new ResourceNotFoundException("Product not found in favorites");
        }
        userRepository.save(user);
        return product;
    }


}
