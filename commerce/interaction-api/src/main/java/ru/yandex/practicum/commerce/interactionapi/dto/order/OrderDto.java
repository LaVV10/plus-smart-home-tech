package ru.yandex.practicum.commerce.interactionapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.commerce.interactionapi.dto.warehouse.AddressDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID orderId;
    private String username;
    private UUID shoppingCartId;
    private Map<UUID, Long> products = new HashMap<>();
    private UUID paymentId;
    private UUID deliveryId;
    private OrderState orderState;
    private Double deliveryWeight;
    private Double deliveryVolume;
    private Boolean fragile;
    private Double totalPrice;
    private Double productPrice;
    private Double deliveryPrice;
    private AddressDto deliveryAddress;
}
