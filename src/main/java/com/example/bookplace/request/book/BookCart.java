package com.example.bookplace.request.book;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookCart {
    private Long bookId;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer quantity;
}
