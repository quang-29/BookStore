package com.example.bookplace.request.order;

import com.example.bookplace.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailDTO {
    private Long id;
    private String address;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private Integer quantity;

    private UserDTO user;
    private BookDTO book;

    public OrderDetailDTO(Long id, String address, OrderStatus status, BigDecimal totalPrice, LocalDateTime createdAt,
                          Integer quantity, Long userId, String userName, String userEmail,
                          Long bookId, String bookTitle, String bookAuthor, BigDecimal bookPrice)
    {
        this.id = id;
        this.address = address;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.quantity = quantity;
        this.user = new UserDTO(userId, userName, userEmail);
        this.book = new BookDTO(bookId, bookTitle, bookAuthor, bookPrice);
    }

    @Getter
    @Setter
    public static class UserDTO {
        private Long userId;
        private String userName;
        private String userEmail;


        public UserDTO(Long userId, String userName, String userEmail) {
            this.userId = userId;
            this.userName = userName;
            this.userEmail = userEmail;
        }
    }

    @Getter
    @Setter
    public static class BookDTO {
        private Long bookId;
        private String bookTitle;
        private String bookAuthor;
        private BigDecimal bookPrice;

        public BookDTO(Long bookId, String bookTitle, String bookAuthor, BigDecimal bookPrice) {
            this.bookId = bookId;
            this.bookTitle = bookTitle;
            this.bookAuthor = bookAuthor;
            this.bookPrice = bookPrice;
        }
    }
}
