package com.example.bookstore.service.imp;

import com.example.bookstore.dto.BookDTO;

import java.util.List;

public interface BookServiceImp {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(int id);
}
