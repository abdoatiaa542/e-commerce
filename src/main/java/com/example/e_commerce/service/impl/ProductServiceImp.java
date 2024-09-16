package com.example.e_commerce.service.impl;


import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.Category;
import com.example.e_commerce.models.entity.Nutrition;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.reposatory.CategoryRepository;
import com.example.e_commerce.reposatory.NutritionRepository;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.service.utils.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NutritionRepository nutritionRepository;

    @Autowired
    private BannerServiceImp bannerServiceImp;


    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortcol, boolean isAc) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(isAc ? Sort.Direction.ASC : Sort.Direction.DESC, sortcol));
        return productRepository.findAll(pageable);
    }


    @Override
    public Page<Product> getProductsByText(String name, int pageNumber, int pageSize, String sortcol, boolean isAc) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(isAc ? Sort.Direction.ASC : Sort.Direction.DESC, sortcol));
        return productRepository.searchByText(name, page);
    }


    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product addProduct(Product product) {

        product.setRate(BigDecimal.ZERO);
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Nutrition nutrition = nutritionRepository.findById(product.getNutrition().getId())
                .orElseThrow(() -> new RuntimeException("Nutrition not found"));
        product.setCategory(category);
        product.setNutrition(nutrition);

        String imageUrl = bannerServiceImp.uploadImageToExternalService(product.getImageUrl());
        product.setImageUrl(imageUrl);
        return productRepository.save(product);
    }

    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
        return product;
    }

    @Override
    @Transactional
    public List<Product> getBestSellingProducts() {
        return productRepository.getBestSellingProducts();
    }

}




