package com.example.bookplace.request.book;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
public class BookUpdate {
    private String title;
    private BigDecimal price;
    private String description;
    private String language;
    private String imagePath;
    private String isbn;
    private Integer page;
    private String publisher;
    private Integer reprint;
    private Long stock;
    private Date publishedDate;
    private String authorName;
}
