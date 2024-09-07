package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.CategoryDto;
import com.example.e_commerce.models.entity.Category;
import com.example.e_commerce.models.mappers.CategoryMapper;
import com.example.e_commerce.service.utils.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class categoriesController {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categoryMapper.toDtoList(categories));
    }


}
