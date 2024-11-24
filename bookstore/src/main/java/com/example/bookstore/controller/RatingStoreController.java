package com.example.bookstore.controller;

import com.example.bookstore.dto.RatingStoreDTO;
import com.example.bookstore.dto.request.RatingStoreRequest;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.entity.RatingStore;
import com.example.bookstore.service.imp.RatingStoreServiceImp;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("/ratingStore")
public class RatingStoreController {
    @Autowired
    RatingStoreServiceImp ratingStoreServiceImp;

    @PostMapping("/createRatingStore")
    public ResponseEntity<DataResponse> createRatingStore(@RequestBody RatingStoreRequest ratingStoreRequest) {
        boolean isCreateRating = ratingStoreServiceImp.createRatingStore(ratingStoreRequest);
        DataResponse dataResponse = DataResponse.builder()
                .data(isCreateRating)
                .message(isCreateRating ? "Create rating successfully": "Create rating failed")
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/getAllRatingByStoreId/{id}")
    public ResponseEntity<DataResponse> getAllRatingByStoreId(@PathVariable Integer id) {
        List<RatingStoreDTO> ratingStoreDTOS = ratingStoreServiceImp.getAllRatingStore(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(ratingStoreDTOS)
                .build();
        return ResponseEntity.ok(dataResponse);
    }
}
