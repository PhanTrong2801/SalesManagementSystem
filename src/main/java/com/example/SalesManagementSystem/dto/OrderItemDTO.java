package com.example.SalesManagementSystem.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long orderItemId;

    private Long orderId;       // chỉ lưu ID thay vì object Order
    private Long productId;     // ID sản phẩm
    private String productName; // tên sản phẩm (nếu muốn hiển thị)

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private BigDecimal total;
}
