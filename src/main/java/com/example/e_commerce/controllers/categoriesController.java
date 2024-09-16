package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.CategoryDtoRequest;
import com.example.e_commerce.models.dto.CategoryResponseDto;
import com.example.e_commerce.models.entity.Category;
import com.example.e_commerce.models.mappers.CategoryRequestMapper;
import com.example.e_commerce.models.mappers.CategoryResponseMapper;
import com.example.e_commerce.service.utils.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class categoriesController {


    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CategoryResponseMapper categoryResponseMapper;
    @Autowired
    private CategoryRequestMapper categoryRequestMapper;


    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<Category> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categoryResponseMapper.toDtoList(categories));
    }


    @GetMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryDtoRequest categoryDto) {
        Category category = categoriesService.addCategory(categoryRequestMapper.toEntity(categoryDto));
        return ResponseEntity.ok(categoryResponseMapper.toDto(category));
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable Integer id) {
        Category category = categoriesService.deleteCategory(id);
        return ResponseEntity.ok(categoryResponseMapper.toDto(category));
    }


}
