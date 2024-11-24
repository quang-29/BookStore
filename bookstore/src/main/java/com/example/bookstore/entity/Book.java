package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_description")
    private String description;

    @Column(name = "book_page")
    private int page;

    @Column(name = "image_book")
    private String imageBook;

    @Column(name = "reprint")
    private int reprint;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<StoreBook> storeBooks;

    @OneToMany(mappedBy = "book")
    private List<RatingBook> ratingBooks;

    @OneToMany(mappedBy = "book")
    private  List<OrderDetail> orderDetails;

}
