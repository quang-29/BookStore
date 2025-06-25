package com.example.bookplace.services.author;

import com.example.bookplace.models.Author;
import com.example.bookplace.request.author.AuthorCreate;
import com.example.bookplace.request.author.AuthorUpdate;

import java.util.List;

public interface AuthorService {
    String addAuthor(AuthorCreate authorCreate);

    String updateAuthor(Long id, AuthorUpdate authorUpdate);

    List<Author> getAllAuthor(int pageSize, int pageNumber);

    Author getAuthorById(Long id);

    Author getAuthorByBookId(Long bookId);
}
