package com.example.bookplace.repositories;

import com.example.bookplace.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where name = :name", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);

    @Query(value = "select * from category where id =:id", nativeQuery = true)
    Optional<Category> findById(@Param("id") Long id);
}
