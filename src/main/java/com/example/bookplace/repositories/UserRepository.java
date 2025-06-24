package com.example.bookplace.repositories;

import com.example.bookplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from user WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
}
