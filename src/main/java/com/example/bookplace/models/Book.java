package com.example.bookplace.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Column(name = "book_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "page", nullable = false)
    private Integer page;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "reprint")
    private Integer reprint;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "sold", nullable = false)
    private Long sold;

    @Temporal(TemporalType.DATE)
    @Column(name = "publishedDate", nullable = false)
    private Date publishedDate;
}

