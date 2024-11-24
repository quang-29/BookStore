package com.example.bookstore.service;

import com.example.bookstore.dto.CategoryDTO;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public boolean addCategory(String name) {
        if(!categoryRepository.existsByName(name)){
            Category category = new Category();
            category.setName(name);
            categoryRepository.save(category);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoriesDTO.add(categoryDTO);
        }
        return categoriesDTO;
    }
}
