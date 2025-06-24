package com.example.bookplace.repositories;

import com.example.bookplace.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * from author where name = :authorName ", nativeQuery = true)
    Author findByName(@Param("authorName") String authorName);
}
