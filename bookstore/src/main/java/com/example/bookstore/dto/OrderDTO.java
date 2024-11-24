package com.example.bookstore.dto;

import com.example.bookstore.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int userId;
    private int storeId;
    private LocalDateTime orderAt;
    private double totalPrice;
    private List<OrderDetailDTO> books;
}
