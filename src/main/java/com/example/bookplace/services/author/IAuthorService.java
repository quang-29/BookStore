package com.example.bookplace.services.author;

import com.example.bookplace.models.Author;
import com.example.bookplace.repositories.AuthorRepository;
import com.example.bookplace.request.author.AuthorCreate;
import com.example.bookplace.request.author.AuthorUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    public IAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public String addAuthor(AuthorCreate authorCreate) {
        Optional<Author> optionalAuthor = authorRepository.findByName(authorCreate.getAuthorName());
        if (optionalAuthor.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author already exists");
        }
        Author author = new Author();
        author.setAuthorName(authorCreate.getAuthorName());
        author.setBio(authorCreate.getBio());
        author.setDob(authorCreate.getDob());
        author.setHometown(authorCreate.getHometown());
        authorRepository.save(author);
        return "Add author successfully";
    }

    @Transactional
    @Override
    public String updateAuthor(Long id, AuthorUpdate authorUpdate) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        foundAuthor.get().setAuthorName(authorUpdate.getAuthorName());
        foundAuthor.get().setBio(authorUpdate.getBio());
        foundAuthor.get().setDob(authorUpdate.getDob());
        foundAuthor.get().setHometown(authorUpdate.getHometown());
        authorRepository.save(foundAuthor.get());
        return "Update author successfully";
    }

    @Override
    public List<Author> getAllAuthor(int pageSize, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageSize, pageSize);
        Page<Author> authors = authorRepository.findAll(pageRequest);
        return authors.getContent();
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id not found");
        }
        return foundAuthor.get();
    }

    @Override
    public Author getAuthorByBookId(Long bookId) {
        Optional<Author> optionalAuthor = authorRepository.getAuthorByBookId(bookId);
        if(optionalAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        return optionalAuthor.get();
    }
}
