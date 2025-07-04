package com.example.bookplace.repositories;

import com.example.bookplace.models.Book;
import com.example.bookplace.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Query(value = "SELECT * FROM order_item WHERE order_id = :orderId", nativeQuery = true)
    List<OrderItem> findAllByOrderId(@Param("orderId") Long id);
}
