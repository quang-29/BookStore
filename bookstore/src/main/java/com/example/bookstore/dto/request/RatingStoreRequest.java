package com.example.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingStoreRequest {
    private String content;
    private int ratePoint;
    private LocalDateTime createdAt;
    private int storeId;
    private int userId;
}
