package com.example.bookplace.repositories;

import com.example.bookplace.models.Order;
import com.example.bookplace.request.order.OrderDetailDTO;
import com.example.bookplace.request.order.OrderResponse;
import com.example.bookplace.request.order.OrderResponseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where id = :id", nativeQuery = true)
    Optional<Order> findById(@Param("id") Long id);


    @Query(value = "select * from orders where user_id= :userId", nativeQuery = true)
    Page<Order> findAllOrdersByUserId(@Param("userId") Long userId, Pageable pageable);




}
