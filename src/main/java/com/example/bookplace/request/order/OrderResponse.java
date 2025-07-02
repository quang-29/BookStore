package com.example.bookplace.request.order;

import com.example.bookplace.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Getter
@Setter

public class OrderResponse {
    private Long id;
    private Long user_id;
    private String address;
    private OrderStatus status;
    private BigDecimal total_rice;
    private LocalDateTime created_at;
    private Long book_id;
    private Integer quantity;
}
