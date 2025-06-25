package com.example.bookplace.request.order;

import com.example.bookplace.request.book.BookCart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreate {
    private Long userId;
    private String address;
    private List<BookCart> bookList;

}
