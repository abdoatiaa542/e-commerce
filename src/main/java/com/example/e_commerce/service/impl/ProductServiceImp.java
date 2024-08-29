package com.example.e_commerce.service.impl;


import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.service.utils.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    // handle the error also
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;

    }

    @Override
    public List<Product> getProductsByText(String name) {
        List<Product> products = productRepository.searchByText(name);
        return products;
    }


    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}




