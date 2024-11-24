package com.example.bookstore.dto.request;


import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingBookRequest {
    private String content;
    private int ratePoint;
    private LocalDateTime createdAt;
    private int bookId;
    private int userId;
}
