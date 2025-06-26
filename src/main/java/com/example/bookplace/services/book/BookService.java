package com.example.bookplace.services.book;

import com.example.bookplace.models.Book;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    String addBook(BookCreate bookCreate);
    String updateBook(Long id, BookUpdate bookUpdate);
    String deleteBook(Long id);
    Page<Book> getAllBooks(Pageable pageable);
    Book getBookById(Long id);
    List<Book> getBooksByCategoryId(Long categoryId);

}
