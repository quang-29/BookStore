package com.example.bookstore.service;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.request.AuthorRequest;
import com.example.bookstore.entity.Author;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.service.imp.AuthorServiceImp;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements AuthorServiceImp {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public AuthorDTO getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.AUTHOR_NOT_FOUND));
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());
        authorDTO.setBio(author.getBio());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAddress(author.getAddress());
        authorDTO.setPhone(author.getPhone());
        return authorDTO;

    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author: authors) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setName(author.getName());
            authorDTO.setBio(author.getBio());
            authorDTO.setEmail(author.getEmail());
            authorDTO.setAddress(author.getAddress());
            authorDTO.setPhone(author.getPhone());
            authorDTOList.add(authorDTO);
        }
        return authorDTOList;
    }

    @Override
    public boolean addAuthor(AuthorRequest authorRequest) {
        Optional<Author> authorFound = authorRepository.findByBio(authorRequest.getBio());
        if(!authorFound.isPresent()) {
            Author author = new Author();
            author.setName(authorRequest.getName());
            author.setBio(authorRequest.getBio());
            author.setEmail(authorRequest.getEmail());
            author.setAddress(authorRequest.getAddress());
            author.setPhone(authorRequest.getPhone());
            authorRepository.save(author);
            return true;
        }
        return false;

    }

    @Override
    public boolean updateAuthor(int id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.AUTHOR_NOT_FOUND));
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());
        author.setEmail(authorRequest.getEmail());
        author.setAddress(authorRequest.getAddress());
        author.setPhone(authorRequest.getPhone());
        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean deleteAuthor(int id) {
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
