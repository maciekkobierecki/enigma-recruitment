package com.enigma.recruitment.task2.payment.client;

import com.enigma.recruitment.task2.payment.PaymentProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentClientConfiguration {

    private final PaymentProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(
                "Authorization",
                String.format("Bearer %s", properties.getAuthToken())
        );
    }
}
