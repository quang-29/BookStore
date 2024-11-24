package com.example.bookstore.service.imp;

import com.example.bookstore.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceImp {

    boolean addCategory(String name);
    List<CategoryDTO> getAllCategories();
}
