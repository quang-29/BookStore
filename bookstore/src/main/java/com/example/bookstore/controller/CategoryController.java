package com.example.bookstore.controller;

import com.example.bookstore.dto.CategoryDTO;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @PostMapping("/addCategory")
    public ResponseEntity<DataResponse> addCategory(@RequestParam String name){
        boolean addSuccess = categoryServiceImp.addCategory(name);
        DataResponse dataResponse = DataResponse.builder()
                .message(addSuccess?"Added successfully":"Added failed")
                .data(addSuccess).build();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<DataResponse> getAllCategories(){
        List<CategoryDTO> categoryDTOList = categoryServiceImp.getAllCategories();
        DataResponse dataResponse = DataResponse.builder()
                .data(categoryDTOList).build();
        return ResponseEntity.ok(dataResponse);
    }



}
