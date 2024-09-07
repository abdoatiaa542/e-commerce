package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {



    public Page<Product> getAllProducts(int pageNumber , int pageSize , String sortcol , boolean isAc);

    public Page<Product> getProductsByText(String name , int pageNumber , int pageSize , String sortcol , boolean isAc); ;

    public  Product getProductById(int id);

    }
