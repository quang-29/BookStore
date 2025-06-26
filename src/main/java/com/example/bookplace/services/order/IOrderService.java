package com.example.bookplace.services.order;

import com.example.bookplace.enums.OrderStatus;
import com.example.bookplace.models.Book;
import com.example.bookplace.models.Order;
import com.example.bookplace.models.OrderItem;
import com.example.bookplace.repositories.BookRepository;
import com.example.bookplace.repositories.OrderItemRepository;
import com.example.bookplace.repositories.OrderRepository;
import com.example.bookplace.request.book.BookCart;
import com.example.bookplace.request.order.OrderCreate;
import com.example.bookplace.request.order.OrderUpdate;
import com.example.bookplace.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IOrderService implements OrderService {

    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public IOrderService(OrderItemRepository orderItemRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public String placeOrder(OrderCreate orderCreate) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        order.setUserId(orderCreate.getUserId());
        order.setAddress(orderCreate.getAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        for(BookCart item: orderCreate.getBookList()){
            Optional<Book> book = bookRepository.findById(item.getBookId());
            if (book.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found");
            }
            if(item.getQuantity() > book.get().getStock()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Book is not enough quantity ");
            }
            BigDecimal itemPrice = book.get().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(Long.valueOf(item.getBookId()));
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrderId(savedOrder.getId());
            orderItemRepository.save(orderItem);
        };
        savedOrder.setTotalPrice(totalPrice);
        orderRepository.save(savedOrder);
        return "Order has been Successfully placed";
    }

    @Override
    public String updateOrder(OrderUpdate orderUpdate) {
        return "";
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found");
        }
        return order.get();
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders;
    }

    @Override
    public Page<Order> getAllOrdersByUserId(Long userId,Pageable pageable) {
        Page<Order> orders = orderRepository.findAllOrdersByUserId(userId);
        return orders;
    }
}
