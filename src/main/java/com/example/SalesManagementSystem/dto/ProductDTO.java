package com.example.SalesManagementSystem.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String sku;
    private String name;
    private String description;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private String unit;
    private Long categoryId;
    private Long supplierId;
}
