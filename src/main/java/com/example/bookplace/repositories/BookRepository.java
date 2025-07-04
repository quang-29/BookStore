package com.example.bookplace.repositories;

import com.example.bookplace.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from book where id= :id",nativeQuery = true)
    Optional<Book> findById(@Param("id") Long id);


    @Query(value = "select * from book where category_id = :categoryId", nativeQuery = true)
    List<Book> getAllBookByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select * from book where title = :title", nativeQuery = true)
    Optional<Book> findByTitle(@Param("title") String title);

}
