package com.enigma.recruitment.task2.payment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {

    @NonNull
    private String name;

    @NonNull
    private BigDecimal unitPrice;

    @NonNull
    private Integer quantity;
}
