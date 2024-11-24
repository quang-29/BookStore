package com.example.bookstore.service.imp;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.request.OrderRequest;
import com.example.bookstore.entity.Order;

import java.util.List;

public interface OrderServiceImp {
    boolean createOrder(OrderRequest orderRequest);
    OrderDTO getOrderById(int id);
    List<OrderDTO> viewAllMyOrders(String name);
}
