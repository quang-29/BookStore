package com.example.bookplace.controllers;


import com.example.bookplace.models.Category;
import com.example.bookplace.request.category.CategoryCreate;
import com.example.bookplace.request.category.CategoryUpdate;
import com.example.bookplace.response.PageResponse;
import com.example.bookplace.services.category.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/add")
    public String addCategory(@RequestBody CategoryCreate categoryCreate) {
        String result = categoryService.addCategory(categoryCreate.getName());
        return result;
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody CategoryUpdate categoryUpdate) {
        String result = categoryService.updateCategory(id,categoryUpdate);
        return result;
    }

    @GetMapping("/all")
    public Page<Category> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategories(pageable);
        return categories;
    }

}
