package com.example.bookplace.mapper;


import com.example.bookplace.models.Book;
import com.example.bookplace.request.book.BookCreate;
import com.example.bookplace.request.book.BookUpdate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class BookMapper {

    public Book mapToBook(BookCreate bookCreate) {
        Book book = new Book();
        book.setTitle(bookCreate.getTitle());
        book.setPrice(bookCreate.getPrice());
        book.setAverageRating(bookCreate.getAverageRating());
        book.setDescription(bookCreate.getDescription());
        book.setLanguage(bookCreate.getLanguage());
        book.setImagePath(bookCreate.getImagePath());
        book.setImagePath(bookCreate.getImagePath());
        book.setIsbn(bookCreate.getIsbn());
        book.setPage(bookCreate.getPage());
        book.setPublisher(bookCreate.getPublisher());
        book.setReprint(bookCreate.getReprint());
        book.setStock(bookCreate.getStock());
        book.setSold(bookCreate.getSold());
        book.setPublishedDate(bookCreate.getPublishedDate());
        return book;
    }

    public void updateBookFromDto(BookUpdate bookUpdate, Book existingBook) {
        existingBook.setTitle(bookUpdate.getTitle());
        existingBook.setPrice(bookUpdate.getPrice());
        existingBook.setDescription(bookUpdate.getDescription());
        existingBook.setLanguage(bookUpdate.getLanguage());
        existingBook.setImagePath(bookUpdate.getImagePath());
        existingBook.setIsbn(bookUpdate.getIsbn());
        existingBook.setPage(bookUpdate.getPage());
        existingBook.setPublisher(bookUpdate.getPublisher());
        existingBook.setReprint(bookUpdate.getReprint());
        existingBook.setStock(bookUpdate.getStock());
        existingBook.setPublishedDate(bookUpdate.getPublishedDate());
    }
}
