package com.example.bookplace.services.book;

import com.example.bookplace.models.Book;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBookService implements BookService {

    @Override
    public String addBook(BookCreate bookCreate) {
        return "";
    }

    @Override
    public String updateBook(BookUpdate bookUpdate) {
        return "";
    }

    @Override
    public String deleteBook(Long id) {
        return "";
    }

    @Override
    public List<Book> getAllBooks(int pageSize, int pageNumber) {
        return List.of();
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public List<Book> getBooksByCategoryId(Long categoryId) {
        return List.of();
    }
}
