package com.example.bookplace.services.category;

import com.example.bookplace.models.Category;
import com.example.bookplace.request.category.CategoryUpdate;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    String addCategory(String name);

    String updateCategory(Long id, CategoryUpdate categoryUpdate);

    Page<Category> getAllCategories(Pageable pageable);
}
