package com.example.bookplace.services.order;

import com.example.bookplace.models.Order;
import com.example.bookplace.request.order.OrderCreate;
import com.example.bookplace.request.order.OrderUpdate;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderCreate orderCreate);
    String updateOrder(OrderUpdate orderUpdate);
    Order getOrderById(Long id);
    List<Order> getAllOrders(int pageSize, int pageNumber);
    List<Order> getAllOrdersByUserId(Long userId);


}
