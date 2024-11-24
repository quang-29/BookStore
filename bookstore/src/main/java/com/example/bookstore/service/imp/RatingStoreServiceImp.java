package com.example.bookstore.service.imp;

import com.example.bookstore.dto.RatingStoreDTO;
import com.example.bookstore.dto.request.RatingStoreRequest;
import com.example.bookstore.entity.RatingStore;

import java.util.List;

public interface RatingStoreServiceImp {
    boolean createRatingStore(RatingStoreRequest ratingStoreRequest);
    List<RatingStoreDTO> getAllRatingStore(int id);
}
