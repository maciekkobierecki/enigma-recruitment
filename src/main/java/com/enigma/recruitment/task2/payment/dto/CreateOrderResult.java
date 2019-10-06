package com.enigma.recruitment.task2.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CreateOrderResult {
    @NonNull
    private String redirectUri;

    @NonNull
    private String orderId;
}
