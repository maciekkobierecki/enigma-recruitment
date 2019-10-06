package com.enigma.recruitment.task2.payment;

import com.enigma.recruitment.task2.payment.client.PaymentClient;
import com.enigma.recruitment.task2.payment.client.dto.CreateOrderRequest;
import com.enigma.recruitment.task2.payment.client.dto.CreateOrderResponse;
import com.enigma.recruitment.task2.payment.client.dto.OrderStatusCode;
import com.enigma.recruitment.task2.payment.dto.CreateOrderCommand;
import com.enigma.recruitment.task2.payment.dto.CreateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final String CURRENCY_CODE = "PLN";

    private final PaymentClient paymentClient;

    private final PaymentProperties properties;

    @Override
    public CreateOrderResult createOrder(CreateOrderCommand command) {
        try {
            return internalCreateOrder(command);
        } catch (Exception e) {
            log.error(String.format("Error occurred while creating order: %s", command), e);

            throw new PaymentException(String.format("Cannot create order for command %s", command));
        }
    }

    private CreateOrderResult internalCreateOrder(CreateOrderCommand command) {
        final CreateOrderResponse response = paymentClient.createOrder(
                CreateOrderRequest.builder()
                        .notifyUrl(properties.getNotifyUrl())
                        .customerIp(command.getCustomerIp())
                        .merchantPosId(command.getMerchantPosId())
                        .description(command.getDescription())
                        .currencyCode(CURRENCY_CODE)
                        .totalAmount(calculateTotalAmount(command))
                        .products(command.getProducts())
                        .build()
        );

        checkOrderStatus(response);

        return new CreateOrderResult(response.getRedirectUri(), response.getOrderId());
    }

    private BigDecimal calculateTotalAmount(CreateOrderCommand command) {
        return command.getProducts().stream()
                .map(product -> product.getUnitPrice().multiply(new BigDecimal(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(IllegalStateException::new);
    }

    private void checkOrderStatus(CreateOrderResponse response) {
        if (response.getStatus().getStatusCode() != OrderStatusCode.SUCCESS) {
            throw new IllegalStateException(String.format("Payment service responded with statusCode = %s", response.getStatus().getStatusCode()));
        }
    }
}
