package com.example.bookstore.repository;

import com.example.bookstore.entity.TokenInValid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenRepository extends JpaRepository<TokenInValid, Integer> {

    boolean existsById(String id);

    boolean existsByToken(String token);
}
