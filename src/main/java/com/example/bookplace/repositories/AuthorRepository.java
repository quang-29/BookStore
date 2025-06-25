package com.example.bookplace.repositories;

import com.example.bookplace.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * from author where name = :authorName ", nativeQuery = true)
    Optional<Author> findByName(@Param("authorName") String authorName);

    @Query(value = "SELECT * FROM author WHERE id = (SELECT author_id FROM book WHERE id = :bookId)", nativeQuery = true)
    Optional<Author> getAuthorByBookId(@Param("bookId") Long bookId);

}
