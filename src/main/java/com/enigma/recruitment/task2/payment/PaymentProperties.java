package com.enigma.recruitment.task2.payment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Component
@Validated
@ConfigurationProperties("payment")
public class PaymentProperties {

    @NotBlank
    private String authToken;

    @NotBlank
    private String notifyUrl;

    @NotBlank
    private String merchantPosId;
}
