package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ProductDetailsDto;
import com.example.e_commerce.models.dto.ProductRequestDto;
import com.example.e_commerce.models.dto.ProductResponseDto;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.mappers.ProductDetailsMapper;
import com.example.e_commerce.models.mappers.ProductRequestMapper;
import com.example.e_commerce.models.mappers.ProductResponseMapper;
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
    private ProductResponseMapper productResponseMapper;
    @Autowired
    private ProductRequestMapper productRequestMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;


    @GetMapping("/best_selling")
    public ResponseEntity<List<ProductResponseDto>> getBestSellingProducts() {
        List<Product> products = productService.getBestSellingProducts();
        return ResponseEntity.ok(productResponseMapper.toProductDtoList(products));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.toEntity(productRequestDto);
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(productResponseMapper.toDto(newProduct));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable int id) {
        Product product = productService.deleteProduct(id);
        return ResponseEntity.ok(productResponseMapper.toDto(product));
    }


    @GetMapping("/get_products")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false, defaultValue = "id") String sortcol, @RequestParam(required = false, defaultValue = "true") boolean isAc) {

        Page<Product> products;
        if (name == null) {
            products = productService.getAllProducts(page, size, sortcol, isAc);
        } else {
            products = productService.getProductsByText(name, page, size, sortcol, isAc);
        }

        List<ProductResponseDto> productResponseDtos = productResponseMapper.toProductDtoList(products.getContent());
        return ResponseEntity.ok(productResponseDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductsDetails(@PathVariable int id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productDetailsMapper.toDto(product));
    }

}
