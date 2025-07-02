package com.example.bookplace.request.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderResponseProjection {
    Long getId();
    Long getUserId();
    String getAddress();
    String getStatus();
    BigDecimal getTotalPrice();
    LocalDateTime getCreatedAt();

    Long getBookId();
    Integer getQuantity();

    String getBookTitle();
    String getBookAuthor();
    BigDecimal getBookPrice();

    String getUserName();
    String getUserEmail();
}
