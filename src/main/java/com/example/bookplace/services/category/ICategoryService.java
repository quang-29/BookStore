package com.example.bookplace.services.category;

import com.example.bookplace.models.Category;
import com.example.bookplace.repositories.CategoryRepository;
import com.example.bookplace.request.category.CategoryUpdate;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public ICategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public String addCategory(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setThumbnail("https://res.cloudinary.com/dmotq51fh/image/upload/v1750817298/icon_vigaa2.svg");
        categoryRepository.save(newCategory);
        return "Add Category successfully";
    }

    @Transactional
    @Override
    public String updateCategory(Long id, CategoryUpdate categoryUpdate) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        Category category = optionalCategory.get();
        category.setName(categoryUpdate.getName());
        category.setThumbnail(categoryUpdate.getThumbnail());
        categoryRepository.save(category);
        return "Update Category successfully";
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }
}
