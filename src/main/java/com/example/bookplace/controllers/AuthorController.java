package com.example.bookplace.controllers;

import com.example.bookplace.models.Author;
import com.example.bookplace.request.author.AuthorCreate;
import com.example.bookplace.request.author.AuthorUpdate;
import com.example.bookplace.response.PageResponse;
import com.example.bookplace.services.author.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorCreate authorCreate) {
        String result = authorService.addAuthor(authorCreate);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdate authorUpdate) {
        String result = authorService.updateAuthor(id,authorUpdate);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Author>> getAllAuthors(Pageable pageable){
        Page<Author> authors = authorService.getAllAuthor(pageable);
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Author> getAuthorByBookId(@PathVariable("bookId") Long bookId) {
        Author author = authorService.getAuthorByBookId(bookId);
        return ResponseEntity.ok(author);
    }

}
