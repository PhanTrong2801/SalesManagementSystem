package com.example.SalesManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(unique = true, length = 100)
    private String email;
    private String phone;
    private String address;
    private String type;

    private Double debt;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}