package com.example.bookplace.services.order;

import com.example.bookplace.models.Order;
import com.example.bookplace.request.order.*;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderCreate orderCreate);
    String updateOrder(OrderUpdate orderUpdate);
    Order getOrderById(Long id);
    Page<Order> getAllOrders(Pageable pageable);
    Page<Order> getAllOrdersByUserId(Long userId, Pageable pageable);


}
