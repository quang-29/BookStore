package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.request.OrderRequest;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.entity.Order;
import com.example.bookstore.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("/createOrder")
    public ResponseEntity<DataResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        boolean isCreateSuccess = orderServiceImp.createOrder(orderRequest);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(isCreateSuccess);
        dataResponse.setMessage(isCreateSuccess ? "Create order successfully" : "Create order failed");
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/viewOrderById/{id}")
    public ResponseEntity<DataResponse> viewOrderById(@PathVariable("id") int id) {
        OrderDTO orderDTO = orderServiceImp.getOrderById(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(orderDTO);
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/viewAllMyOrders")
    public ResponseEntity<DataResponse> viewAllMyOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<OrderDTO> orderDTOList = orderServiceImp.viewAllMyOrders(auth.getName());
        return ResponseEntity.ok(DataResponse.builder()
                        .data(orderDTOList)
                .build());
    }
}
