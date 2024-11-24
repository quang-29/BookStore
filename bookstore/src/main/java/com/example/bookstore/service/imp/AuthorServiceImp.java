package com.example.bookstore.service.imp;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.request.AuthorRequest;
import com.example.bookstore.entity.Author;

import java.util.List;

public interface AuthorServiceImp {
    AuthorDTO getAuthorById(int id);
    List<AuthorDTO> getAuthors();
    boolean addAuthor(AuthorRequest authorRequest);
    boolean updateAuthor(int id, AuthorRequest authorRequest);
    boolean deleteAuthor(int id);
}
