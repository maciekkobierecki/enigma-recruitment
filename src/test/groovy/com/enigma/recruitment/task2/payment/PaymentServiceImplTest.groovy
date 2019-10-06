package com.enigma.recruitment.task2.payment

import com.enigma.recruitment.task2.payment.client.PaymentClient
import com.enigma.recruitment.task2.payment.client.dto.*
import feign.FeignException
import spock.lang.Specification
import spock.lang.Subject

class PaymentServiceImplTest extends Specification {
    @Subject
    PaymentService paymentService

    PaymentClient paymentClient

    def setup() {
        paymentClient = Mock(PaymentClient)
        paymentService = new PaymentServiceImpl(paymentClient, new PaymentProperties())
    }

    def "should createOrder with passed products and calculated totalAmount"() {
        given:
        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        def serviceResponse = CreateOrderResponse.builder()
                .status(new StatusDto(OrderStatusCode.SUCCESS))
                .redirectUri("someUri")
                .orderId("someId")
                .build()


        when:
        paymentService.createOrder(command)

        then:
        1 * paymentClient.createOrder({
            it.customerIp == command.getCustomerIp()
            it.merchantPosId == command.getMerchantPosId()
            it.description == command.getDescription()
            it.currencyCode == "PLN"
            it.totalAmount == new BigDecimal(13)
            it.products == command.getProducts()
        }) >> serviceResponse

    }

    def "should throw PaymentException if there was a problem with external service"() {
        given:
        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        paymentClient.createOrder(_ as CreateOrderRequest) >> { throw new FeignException(1, "test") }

        when:
        paymentService.createOrder(command)

        then:
        thrown(PaymentException)
    }

    def "should not throw if payment service responded with status SUCCESS"() {
        given:
        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        paymentClient.createOrder(_ as CreateOrderRequest) >> CreateOrderResponse.builder()
                .status(new StatusDto(OrderStatusCode.SUCCESS))
                .redirectUri("someUri")
                .orderId("someId")
                .build()

        when:
        paymentService.createOrder(command)

        then:
        noExceptionThrown()
    }

    def "should throw PaymentException if payment service responded with status another than SUCCESS"() {
        given:
        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        paymentClient.createOrder(_ as CreateOrderRequest) >> CreateOrderResponse.builder()
                .status(new StatusDto(OrderStatusCode.FAILURE))
                .redirectUri("someUri")
                .build()

        when:
        paymentService.createOrder(command)

        then:
        thrown(PaymentException)
    }

    def "should throw PaymentException if payment service responded without orderId or redirectUri"() {
        given:
        def command = Fixtures.someCreateOrderCommand(
                products: [
                        new ProductDto("someName1", BigDecimal.TEN, 1),
                        new ProductDto("someName2", BigDecimal.ONE, 3)
                ]
        )

        paymentClient.createOrder(_ as CreateOrderRequest) >> CreateOrderResponse.builder()
                .status(new StatusDto(OrderStatusCode.SUCCESS))
                .build()

        when:
        paymentService.createOrder(command)

        then:
        thrown(PaymentException)
    }
}
