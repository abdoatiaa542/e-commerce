package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.Category;
import com.example.e_commerce.reposatory.CategoryRepository;
import com.example.e_commerce.service.utils.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriesServiceImp implements CategoriesService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
