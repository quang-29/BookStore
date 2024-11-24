package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_description")
    private String storeDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "image_store")
    private String imageStore;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "open_date")
    private LocalDateTime openDate;

    @OneToMany(mappedBy = "store")
    private List<StoreBook> storeBooks;

    @OneToMany(mappedBy = "store")
    private List<RatingStore> ratingStores;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;


}
