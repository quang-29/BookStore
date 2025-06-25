package com.example.bookplace.request.book;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BookCreate {

    private String title;
    private BigDecimal price;
    private Double averageRating = 0.0;
    private String description;
    private String language;
    private String imagePath;
    private String isbn;
    private Integer page;
    private String publisher;
    private Integer reprint;
    private Long stock;
    private Long sold;
    private Date publishedDate;
    private String authorName;
}
