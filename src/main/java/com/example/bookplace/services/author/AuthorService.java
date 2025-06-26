package com.example.bookplace.services.author;

import com.example.bookplace.models.Author;
import com.example.bookplace.request.author.AuthorCreate;
import com.example.bookplace.request.author.AuthorUpdate;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    String addAuthor(AuthorCreate authorCreate);

    String updateAuthor(Long id, AuthorUpdate authorUpdate);

    Page<Author> getAllAuthor(Pageable pageable);

    Author getAuthorById(Long id);

    Author getAuthorByBookId(Long bookId);
}
