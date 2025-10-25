package com.example.SalesManagementSystem.service.impl;

import com.example.SalesManagementSystem.dto.OrderDTO;
import com.example.SalesManagementSystem.entity.Customer;
import com.example.SalesManagementSystem.entity.Order;
import com.example.SalesManagementSystem.repository.CustomerRepository;
import com.example.SalesManagementSystem.repository.OrderRepository;
import com.example.SalesManagementSystem.repository.ProductRepository;
import com.example.SalesManagementSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    //Tao don hang moi
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(()->new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderNumber(generateOrderNumber());
    }

    // tao ma don hang tu dong
    private String generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = orderRepository.count() +1;
        return String.format("ORD-%s-%04d", datePart, count);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, String status) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }


}
