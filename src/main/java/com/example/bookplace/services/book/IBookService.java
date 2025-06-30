package com.example.bookplace.services.book;

import com.example.bookplace.mapper.BookMapper;
import com.example.bookplace.models.Author;
import com.example.bookplace.models.Book;
import com.example.bookplace.repositories.AuthorRepository;
import com.example.bookplace.repositories.BookRepository;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public IBookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public String addBook(BookCreate bookCreate) {
        Optional<Book> bookOptional = bookRepository.findByTitle(bookCreate.getTitle());
        if (bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book already exists");
        }
        Book book = bookMapper.mapToBook(bookCreate);
        bookRepository.save(book);
        return "Add Book Successfully";
    }

    @Override
    public String updateBook(Long id, BookUpdate bookUpdate) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookMapper.updateBookFromDto(bookUpdate, existingBook);
        bookRepository.save(existingBook);
        return "Update Book Successfully";
    }


    @Override
    public String deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.delete(book);
        return "Book deleted successfully";
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return book;
    }

    @Override
    public List<Book> getBooksByCategoryId(Long categoryId) {
        List<Book> books = bookRepository.getAllBookByCategoryId(categoryId);
        return books;
    }
}
