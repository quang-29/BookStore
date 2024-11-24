package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.OrderDetailDTO;
import com.example.bookstore.dto.request.OrderDetailRequest;
import com.example.bookstore.dto.request.OrderRequest;
import com.example.bookstore.entity.*;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.key.KeyOrderDetail;
import com.example.bookstore.repository.*;
import com.example.bookstore.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class OrderService implements OrderServiceImp {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(UserRepository userRepository, StoreRepository storeRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean createOrder(OrderRequest orderRequest) {
        boolean isCreateSucess = false;
        try {

            User user = userRepository.findById(orderRequest.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Store store = storeRepository.findById(orderRequest.getStoreId())
                    .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));

            Order order = new Order();
            order.setUser (user);
            order.setStore(store);
            order.setCreatedAt(orderRequest.getOrderAt());
            order.setPrice(orderRequest.getPrice());
            Order orderSaved = orderRepository.save(order);

            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetailRequest detailRequest : orderRequest.getDetailRequests()) {
                Book foundBook = bookRepository.findById(detailRequest.getBookId())
                        .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

                OrderDetail orderDetail = new OrderDetail();
                KeyOrderDetail keyOrderDetail = new KeyOrderDetail(orderSaved.getId(), detailRequest.getBookId());
                orderDetail.setKeyOrderDetail(keyOrderDetail);
                orderDetail.setBook(foundBook);
                orderDetail.setQuantity(detailRequest.getQuantity());
                orderDetail.setOrder(orderSaved);
                orderDetailRepository.save(orderDetail);
                orderDetails.add(orderDetail);
            }
            isCreateSucess = true;

        } catch (Exception e) {
            isCreateSucess = false;
            throw new RuntimeException(e);

        }
    return isCreateSucess;

    }

    @Override
    public OrderDTO getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setStoreId(order.getStore().getId());
        orderDTO.setOrderAt(order.getCreatedAt());
        orderDTO.setTotalPrice(order.getPrice());
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

            Book book = bookRepository.findById(orderDetail.getBook().getId())
                            .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
            orderDetailDTO.setName(book.getName());
            orderDetailDTO.setCategory(book.getCategory().getName());
            orderDetailDTO.setAuthor(book.getAuthor().getName());
            orderDetailDTO.setImageBook(book.getImageBook());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());
            orderDetailDTO.setDescription(book.getDescription());
            orderDetailDTO.setPages(book.getPage());
            orderDetailDTO.setPrice(book.getPrice());
            orderDetailDTOS.add(orderDetailDTO);
        }
        orderDTO.setBooks(orderDetailDTOS);
        return orderDTO;

    }

    @Override
    public List<OrderDTO> viewAllMyOrders(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        List<Order> orderList = orderRepository.findAllOrdersByUserId(user.getId());
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orderList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserId(order.getUser().getId());
            orderDTO.setStoreId(order.getStore().getId());
            orderDTO.setOrderAt(order.getCreatedAt());
            orderDTO.setTotalPrice(order.getPrice());
            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setName(orderDetail.getBook().getName());
                orderDetailDTO.setCategory(orderDetail.getBook().getCategory().getName());
                orderDetailDTO.setAuthor(orderDetail.getBook().getAuthor().getName());
                orderDetailDTO.setImageBook(orderDetail.getBook().getImageBook());
                orderDetailDTO.setQuantity(orderDetail.getQuantity());
                orderDetailDTO.setPages(orderDetail.getBook().getPage());
                orderDetailDTO.setPrice(orderDetail.getBook().getPrice());
                orderDetailDTO.setDescription(orderDetail.getBook().getDescription());
                orderDetailDTOS.add(orderDetailDTO);

            }
            orderDTO.setBooks(orderDetailDTOS);
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}