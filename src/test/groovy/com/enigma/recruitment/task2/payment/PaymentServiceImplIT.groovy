package com.enigma.recruitment.task2.payment

import com.enigma.recruitment.task2.payment.client.dto.CreateOrderResponse
import com.enigma.recruitment.task2.payment.client.dto.OrderStatusCode
import com.enigma.recruitment.task2.payment.client.dto.ProductDto
import com.enigma.recruitment.task2.payment.client.dto.StatusDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.netflix.loadbalancer.Server
import com.netflix.loadbalancer.ServerList
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.netflix.ribbon.StaticServerList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

import static com.github.tomakehurst.wiremock.client.WireMock.*

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = [RibbonTestClientConfiguration.class])
class PaymentServiceImplIT extends Specification {
    @Subject
    @Autowired
    PaymentService paymentService

    @Autowired
    ObjectMapper objectMapper

    @Rule
    static WireMockRule WIREMOCK = new WireMockRule(WireMockConfiguration.options().dynamicPort())

    static {
        WIREMOCK.start()
    }

    def 'should call payment service with passed all parameters and products'() {
        given:
        WIREMOCK.stubFor(post(urlPathEqualTo("/api/v2_1/orders"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(toJson(CreateOrderResponse.builder()
                                .status(new StatusDto(OrderStatusCode.SUCCESS))
                                .redirectUri("someUri")
                                .orderId("someOrderId")
                                .build()
                        ))
                )
        )

        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        when:
        paymentService.createOrder(command)

        then:

        WIREMOCK.verify(postRequestedFor(urlPathEqualTo("/api/v2_1/orders"))
                .withRequestBody(containing("\"customerIp\":\"" + command.getCustomerIp() + "\""))
                .withRequestBody(containing("\"merchantPosId\":" + command.getMerchantPosId()))
                .withRequestBody(containing("\"description\":\"" + command.getDescription() + "\""))
                .withRequestBody(containing("\"currencyCode\":\"PLN\""))
                .withRequestBody(containing("\"totalAmount\":13"))
                .withRequestBody(containing("\"products\":[{\"name\":\"someName1\",\"unitPrice\":10,\"quantity\":1},{\"name\":\"someName2\",\"unitPrice\":1,\"quantity\":3}]")))
    }


    @Profile("test")
    @TestConfiguration
    static class RibbonTestClientConfiguration {

        @Bean
        ServerList<Server> ribbonServerList() {
            return new StaticServerList<>(new Server("localhost", WIREMOCK.port()))
        }
    }

    protected String toJson(Object object) {
        return objectMapper.writeValueAsString(object)
    }
}