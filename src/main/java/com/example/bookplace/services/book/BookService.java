package com.example.bookplace.services.book;

import com.example.bookplace.models.Book;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;

import java.util.List;

public interface BookService {
    String addBook(BookCreate bookCreate);
    String updateBook(BookUpdate bookUpdate);
    String deleteBook(Long id);
    List<Book> getAllBooks(int pageSize, int pageNumber);
    Book getBookById(Long id);
    List<Book> getBooksByCategoryId(Long categoryId);

}
