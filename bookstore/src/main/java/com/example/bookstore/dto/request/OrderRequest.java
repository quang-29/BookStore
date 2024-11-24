package com.example.bookstore.dto.request;

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
public class OrderRequest {
        private Integer userId;
        private Integer storeId;
        private double price;
        private LocalDateTime orderAt;
        private List<OrderDetailRequest> detailRequests;
}
