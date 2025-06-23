package com.example.bookplace.repositories;

import com.example.bookplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepositoty extends JpaRepository<User, Long> {
}
