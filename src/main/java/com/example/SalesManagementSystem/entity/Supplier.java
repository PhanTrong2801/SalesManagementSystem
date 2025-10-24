package com.example.SalesManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(nullable = false, length = 150)
    private String name;

    private String contactName;
    private String phone;
    private String email;
    private String address;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
