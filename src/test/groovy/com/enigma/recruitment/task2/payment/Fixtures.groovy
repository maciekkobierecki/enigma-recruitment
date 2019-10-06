package com.enigma.recruitment.task2.payment

import com.enigma.recruitment.task2.payment.client.dto.ProductDto
import com.enigma.recruitment.task2.payment.dto.CreateOrderCommand

class Fixtures {

    static CreateOrderCommand someCreateOrderCommand(Map params = [:]) {
        return CreateOrderCommand.builder()
                .customerIp("127.0.0.1")
                .merchantPosId(12L)
                .description("test description")
                .products((List<ProductDto>) params.getOrDefault("products", []))
                .build()
    }
}
