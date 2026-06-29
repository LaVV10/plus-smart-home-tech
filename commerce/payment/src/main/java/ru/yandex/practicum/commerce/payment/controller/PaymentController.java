package ru.yandex.practicum.commerce.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.commerce.interactionapi.client.PaymentClient;
import ru.yandex.practicum.commerce.interactionapi.dto.order.OrderDto;
import ru.yandex.practicum.commerce.interactionapi.dto.payment.PaymentDto;
import ru.yandex.practicum.commerce.payment.service.PaymentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PaymentController implements PaymentClient {

    private final PaymentService paymentService;

    @Override
    public Double productCost(@RequestBody OrderDto orderDto) {
        return paymentService.productCost(orderDto);
    }

    @Override
    public Double getTotalCost(@RequestBody OrderDto orderDto) {
        return paymentService.getTotalCost(orderDto);
    }

    @Override
    public PaymentDto payment(@RequestBody OrderDto orderDto) {
        return paymentService.payment(orderDto);
    }

    @Override
    public PaymentDto paymentSuccess(@RequestBody UUID paymentId) {
        return paymentService.paymentSuccess(paymentId);
    }

    @Override
    public PaymentDto paymentFailed(@RequestBody UUID paymentId) {
        return paymentService.paymentFailed(paymentId);
    }
}
