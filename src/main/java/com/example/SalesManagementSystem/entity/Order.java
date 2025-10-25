package com.example.SalesManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false, unique = true, length = 50)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();
    @Column(length = 20)
    private String status = "PENDING";// PENDING, PAID, CANCELLED, SHIPPED...

    @Column(precision = 15, scale = 2)
    private BigDecimal subTotal = BigDecimal.ZERO;
    @Column(precision = 15, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;
    @Column(precision = 15, scale = 2)
    private BigDecimal tax = BigDecimal.ZERO;
    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    public void addItem(OrderItem item){
        items.add(item);
        item.setOrder(this);
    }
    public void removeItem(OrderItem item){
        items.remove(item);
        item.setOrder(null);
    }
    public void calculateTotals(){
        this.subTotal = items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountAmount = this.discount != null ? this.discount : BigDecimal.ZERO;
        BigDecimal taxAmount  = this.tax != null ? this.tax : BigDecimal.ZERO;

        this.totalAmount = subTotal.subtract(discountAmount).add(taxAmount);
    }
}