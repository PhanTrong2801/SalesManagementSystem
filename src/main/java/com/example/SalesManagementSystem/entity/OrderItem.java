package com.example.SalesManagementSystem.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    @Column(precision = 15, scale = 2)
    private BigDecimal unitPrice;
    @Column(precision = 15, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;
    @Column(precision = 15, scale = 2)
    private BigDecimal total;

    @PrePersist
    @PreUpdate
    public void calculateTotal(){
        BigDecimal price = unitPrice != null ? unitPrice : BigDecimal.ZERO;
        BigDecimal qty = BigDecimal.valueOf(quantity != null ?quantity : 0);
        BigDecimal discountAmount = discount !=null ? discount : BigDecimal.ZERO;
        BigDecimal rawTotal = price.multiply(qty).subtract(discountAmount);
        // đảm bảo không âm
        BigDecimal nonNegative = rawTotal.max(BigDecimal.ZERO);
        // chuẩn hóa scale 2 chữ số, làm tròn HALF_UP
        this.total = nonNegative.setScale(2, RoundingMode.HALF_UP);
    }
}
