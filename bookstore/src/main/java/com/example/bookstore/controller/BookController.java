package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.service.imp.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceImp bookServiceImp;


    @GetMapping("/getAllBooks")
    private ResponseEntity<DataResponse> getAllBooks() {
        List<BookDTO> bookdtos = bookServiceImp.getAllBooks();
        DataResponse dataResponse = DataResponse.builder()
                .data(bookdtos).build();
        return ResponseEntity.ok(dataResponse);

    }

    @GetMapping("/getBookById/{id}")
    private ResponseEntity<DataResponse> getBookById(@PathVariable("id") int id) {
        BookDTO bookdto = bookServiceImp.getBookById(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(bookdto).build();
        return ResponseEntity.ok(dataResponse);
    }

}
