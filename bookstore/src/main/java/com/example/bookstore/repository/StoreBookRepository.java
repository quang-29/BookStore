package com.example.bookstore.repository;

import com.example.bookstore.entity.StoreBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreBookRepository extends JpaRepository<StoreBook, Integer> {

    List<StoreBook> findBookIdByStoreId(int storeId);
}
