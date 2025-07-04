package com.example.bookplace.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long bookId;
    private String bookTitle;
    private BigDecimal bookPrice;
    private String bookImage;
    private String bookAuthor;
    private Integer quantity;
}
