package com.example.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token_invalid")
public class TokenInValid {

    @Id
    private String id;

    @Column(length = 1024)
    private String token;

    @Column
    private Date expires;
}
