package com.example.matei.service;

import com.example.matei.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order saveOrder(Order order);

    Order getOrderById(Long id);

    Order updateOrder(Order order);

    void deleteOrderById(Long id);
}