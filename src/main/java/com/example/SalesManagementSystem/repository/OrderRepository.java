package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
