package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Product;

import java.util.List;

public interface ProductService {



    public List<Product> getAllProducts();

    public List<Product> getProductsByText(String name) ;

    public  Product getProductById(int id);

    }
