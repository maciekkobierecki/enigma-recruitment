package com.enigma.recruitment.task2.payment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private String notifyUrl;

    private String customerIp;

    private Long merchantPosId;

    private String description;

    private String currencyCode;

    private BigDecimal totalAmount;

    List<ProductDto> products;
}
