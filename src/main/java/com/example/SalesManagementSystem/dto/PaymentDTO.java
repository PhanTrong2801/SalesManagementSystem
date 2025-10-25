package com.example.SalesManagementSystem.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long paymentId;
    private String method;          // ví dụ: CASH, CARD, BANK_TRANSFER...
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}