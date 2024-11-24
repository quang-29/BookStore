package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.imp.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceImp {

    @Autowired
    BookRepository bookRepository;
    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookdtos = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookdto = new BookDTO();
            bookdto.setName(book.getName());
            bookdto.setAuthor(book.getAuthor().getName());
            bookdto.setPages(book.getPage());
            bookdto.setCategory(book.getCategory().getName());
            bookdto.setDescription(book.getDescription());
            bookdto.setReprint(book.getReprint());
            bookdto.setPrice(book.getPrice());
            bookdtos.add(bookdto);

        }
        return bookdtos;
    }

    @Override
    public BookDTO getBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.BOOK_NOT_FOUND));
        BookDTO bookdto = new BookDTO();
        bookdto.setName(book.getName());
        bookdto.setAuthor(book.getAuthor().getName());
        bookdto.setPages(book.getPage());
        bookdto.setCategory(book.getCategory().getName());
        bookdto.setDescription(book.getDescription());
        bookdto.setReprint(book.getReprint());
        bookdto.setPrice(book.getPrice());
        return bookdto;
    }
}
