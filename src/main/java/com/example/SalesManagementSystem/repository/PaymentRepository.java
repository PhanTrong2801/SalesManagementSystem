package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
