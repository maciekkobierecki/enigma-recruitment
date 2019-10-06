package com.enigma.recruitment.task2.payment.dto;

import com.enigma.recruitment.task2.payment.client.dto.ProductDto;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class CreateOrderCommand {

    @NonNull
    private String customerIp;

    @NonNull
    private Long merchantPosId;

    @NonNull
    private String description;

    @NonNull
    private List<ProductDto> products;
}
