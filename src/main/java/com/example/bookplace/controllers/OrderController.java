package com.example.bookplace.controllers;

import com.example.bookplace.models.Book;
import com.example.bookplace.models.Order;
import com.example.bookplace.request.order.OrderCreate;
import com.example.bookplace.request.order.OrderDetailDTO;
import com.example.bookplace.request.order.OrderResponse;
import com.example.bookplace.request.order.OrderResponseProjection;
import com.example.bookplace.response.PageResponse;
import com.example.bookplace.services.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody OrderCreate orderCreate){
        String result = orderService.placeOrder(orderCreate);
        return result;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable){
        Page<Order> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Order>> getBooksByUserId(@PathVariable Long userId, Pageable pageable){
        Page<Order> orders = orderService.getAllOrdersByUserId(userId,pageable);
        return ResponseEntity.ok(orders);
    }

}
