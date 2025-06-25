package com.example.bookplace.services.order;

import com.example.bookplace.models.Order;
import com.example.bookplace.request.order.OrderCreate;
import com.example.bookplace.request.order.OrderUpdate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderService implements OrderService {
    @Override
    public String placeOrder(OrderCreate orderCreate) {
        return "";
    }

    @Override
    public String updateOrder(OrderUpdate orderUpdate) {
        return "";
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders(int pageSize, int pageNumber) {
        return List.of();
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        return List.of();
    }
}
