package com.example.bookstore.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyOrderDetail implements Serializable {

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "book_id")
    private int bookId;
}
