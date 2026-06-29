package ru.yandex.practicum.commerce.interactionapi.dto.payment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private UUID paymentId;
    private UUID orderId;
    private Double productPrice;
    private Double deliveryPrice;
    private Double totalPrice;
    private PaymentState paymentState;
}
