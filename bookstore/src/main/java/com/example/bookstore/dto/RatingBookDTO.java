package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingBookDTO {
    private String content;
    private int ratePoint;
    private LocalDateTime createdAt;
    private String bookName;
    private String username;
}
