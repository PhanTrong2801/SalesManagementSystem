package com.example.SalesManagementSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String type;
    private Double debt;
}
