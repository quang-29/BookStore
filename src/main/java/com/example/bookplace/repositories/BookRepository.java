package com.example.bookplace.repositories;

import com.example.bookplace.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
