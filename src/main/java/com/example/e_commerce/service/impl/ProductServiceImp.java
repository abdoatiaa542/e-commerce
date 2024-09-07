package com.example.e_commerce.service.impl;


import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.service.utils.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;




    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortcol, boolean isAc) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(isAc ? Sort.Direction.ASC : Sort.Direction.DESC, sortcol));
        return productRepository.findAll(pageable);
    }


    @Override
    public Page<Product> getProductsByText(String name, int pageNumber, int pageSize, String sortcol, boolean isAc) {

        Pageable page = PageRequest
                .of(pageNumber, pageSize, Sort.by(isAc ? Sort.Direction.ASC : Sort.Direction.DESC, sortcol));

        Page<Product> products = productRepository.searchByText(name, page);
        return products;
    }


    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}




