package com.example.bookplace.controllers;

import com.example.bookplace.models.Author;
import com.example.bookplace.request.AuthorCreate;
import com.example.bookplace.request.AuthorUpdate;
import com.example.bookplace.services.author.AuthorService;
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

    @PostMapping("/update")
    public ResponseEntity<String> updateAuthor(Long id, @RequestBody AuthorUpdate authorUpdate) {
        String result = authorService.updateAuthor(id,authorUpdate);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber
    ) {
        List<Author> authors = authorService.getAllAuthor(pageSize,pageNumber);
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

}
