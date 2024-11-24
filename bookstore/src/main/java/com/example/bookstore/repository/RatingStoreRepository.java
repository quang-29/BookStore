package com.example.bookstore.repository;

import com.example.bookstore.entity.RatingStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingStoreRepository extends JpaRepository<RatingStore, Integer> {
    List<RatingStore> findRatingStoresByStoreId(int id);
}
