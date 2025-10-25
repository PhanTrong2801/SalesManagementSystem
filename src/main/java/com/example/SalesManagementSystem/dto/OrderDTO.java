package com.example.SalesManagementSystem.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long orderId;
    private String orderNumber;
    private Long customerId;              // chỉ lưu ID của Customer thay vì full object
    private String customerName;          // có thể thêm nếu cần hiển thị tên
    private LocalDateTime orderDate;
    private String status;

    private BigDecimal subTotal;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal totalAmount;

    private List<OrderItemDTO> items;     // danh sách sản phẩm
    private List<PaymentDTO> payments;    // danh sách thanh toán (nếu cần)
}