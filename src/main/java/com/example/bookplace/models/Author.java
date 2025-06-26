package com.example.bookplace.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String authorName;

    @Column(name = "bio",columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "hometown")
    private String hometown;

}
