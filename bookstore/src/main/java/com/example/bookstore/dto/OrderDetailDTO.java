package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private String name;
    private String author;
    private int pages;
    private String category;
    private String description;
    private double price;
    private int quantity;
    private String imageBook;
}
