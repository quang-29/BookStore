package com.example.bookstore.controller;

import com.example.bookstore.dto.RatingBookDTO;
import com.example.bookstore.dto.request.RatingBookRequest;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.entity.RatingBook;
import com.example.bookstore.service.imp.RatingBookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratingBook")
public class RatingBookController {

    @Autowired
    RatingBookServiceImp ratingBookServiceImp;
    @PostMapping("/create")
    public ResponseEntity<DataResponse> createRatingBook(
            @RequestBody RatingBookRequest ratingBookRequest) {
            boolean isCreateRating = ratingBookServiceImp.addRatingBook(ratingBookRequest);
            DataResponse dataResponse = new DataResponse();
            dataResponse.setData(isCreateRating);
            dataResponse.setMessage(isCreateRating ? "Create rating successfully":"Create rating failed");
            return ResponseEntity.ok(dataResponse);
    }
    @GetMapping("/showAllRatingBook/{id}")
    public ResponseEntity<DataResponse> showAllRatingBook(@PathVariable int id){
        List<RatingBookDTO> ratingBookDTOList = ratingBookServiceImp.getRatingBooksById(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(ratingBookDTOList);
        return ResponseEntity.ok(dataResponse);
    }
}
