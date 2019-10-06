package com.enigma.recruitment.task2.payment.client;

import com.enigma.recruitment.task2.payment.client.dto.CreateOrderRequest;
import com.enigma.recruitment.task2.payment.client.dto.CreateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "paymentClient",
        path = "/api/v2_1/"
)
public interface PaymentClient {

    @PostMapping(
            path = "orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
