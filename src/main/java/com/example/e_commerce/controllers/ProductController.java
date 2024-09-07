package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ProductDetailsDto;
import com.example.e_commerce.models.dto.ProductDto;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.mappers.ProductDetailsMapper;
import com.example.e_commerce.models.mappers.ProductMapper;
import com.example.e_commerce.service.utils.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @GetMapping("/get_products")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(required = false) String name,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(required = false, defaultValue = "id") String sortcol,
                                                        @RequestParam(required = false, defaultValue = "true") boolean isAc) {

        Page<Product> products;
        if (name == null) {
            products = productService.getAllProducts(page, size, sortcol, isAc);
        } else {
            products = productService.getProductsByText(name, page, size, sortcol, isAc);
        }

        List<ProductDto> productDtos = productMapper.toProductDtoList(products.getContent());
        return ResponseEntity.ok(productDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductsDetails(@PathVariable int id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productDetailsMapper.toDto(product));
    }


}
