package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.StoreDTO;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.service.imp.StoreServiceImp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreServiceImp storeServiceImp;

    @PostMapping("/createStore")
    public ResponseEntity<DataResponse> createStore(@RequestParam MultipartFile file,
                                                    @RequestParam String storeName,
                                                    @RequestParam String storeDescription,
                                                    @RequestParam String address,
                                                    @RequestParam String email,
                                                    @RequestParam String phone,
                                                    @RequestParam String openDate) {
        boolean isCreateSuccess = storeServiceImp.createStore(file, storeName, storeDescription, address, email, phone, openDate);
        DataResponse response = DataResponse.builder().data(isCreateSuccess).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getStoreById/{id}")
    public ResponseEntity<DataResponse> getStoreById(@PathVariable int id) {
        StoreDTO storeDTO = storeServiceImp.fingStoreById(id);
        DataResponse response = DataResponse.builder().data(storeDTO).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAllStores")
    public ResponseEntity<DataResponse> getAllStores() {
        List<StoreDTO> storeDTOList = storeServiceImp.getAllStores();
        DataResponse response = DataResponse.builder().data(storeDTOList).build();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getAllBooksOfStore/{id}")
    public ResponseEntity<DataResponse> getAllBooksOfStore(@PathVariable int id) {
        List<BookDTO> bookDTOList = storeServiceImp.getAllBooksOfStore(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(bookDTOList).build();
        return ResponseEntity.ok().body(dataResponse);
    }
}
