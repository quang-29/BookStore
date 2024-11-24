package com.example.bookstore.repository;

import com.example.bookstore.entity.RatingBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingBookRepository extends JpaRepository<RatingBook, Integer> {
    List<RatingBook> findAllBooksById(int id);

    List<RatingBook> findAllRatingBookByBookId(int id);
}
