package com.example.bookstore.service.imp;

import com.example.bookstore.dto.RatingBookDTO;
import com.example.bookstore.dto.request.RatingBookRequest;
import com.example.bookstore.entity.RatingBook;

import java.util.List;

public interface RatingBookServiceImp {
    boolean addRatingBook(RatingBookRequest ratingBookRequest);
    List<RatingBookDTO> getRatingBooksById(int id);
}
