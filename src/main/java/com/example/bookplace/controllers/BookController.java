package com.example.bookplace.controllers;

import com.example.bookplace.models.Book;
import com.example.bookplace.repositories.BookRepository;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;
import com.example.bookplace.services.book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookCreate bookCreate){
        String result = bookService.addBook(bookCreate);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookUpdate bookUpdate){
        String result = bookService.updateBook(id,bookUpdate);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable){
        Page<Book> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{categoryId}/book")
    public ResponseEntity<List<Book>> getBooksByCategoryId(@PathVariable Long categoryId) {
        List<Book> books = bookService.getBooksByCategoryId(categoryId);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookById(@RequestParam Long id){
        String result = bookService.deleteBook(id);
        return ResponseEntity.ok(result);
    }

}
