package com.enigma.recruitment.task2.payment;

import com.enigma.recruitment.task2.payment.dto.CreateOrderCommand;
import com.enigma.recruitment.task2.payment.dto.CreateOrderResult;

public interface PaymentService {
    CreateOrderResult createOrder(CreateOrderCommand command);
}
