package com.example.SalesManagementSystem.service;

import com.example.SalesManagementSystem.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByCustomer(Long customerId);
    OrderDTO updateOrderStatus(Long orderId, String status);
    void deleteOrder(Long orderId);
}

