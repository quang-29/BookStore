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
public class KeyStoreBook implements Serializable {
    @Column(name = "store_id")
    private int storeId;

    @Column(name = "book_id")
    private int bookId;

}
