package com.example.bookplace.services.category;

import com.example.bookplace.models.Category;
import com.example.bookplace.request.category.CategoryUpdate;

import java.util.List;

public interface CategoryService {
    String addCategory(String name);

    String updateCategory(Long id, CategoryUpdate categoryUpdate);

    List<Category> getAllCategories(int pageSize, int pageNumber);
}
