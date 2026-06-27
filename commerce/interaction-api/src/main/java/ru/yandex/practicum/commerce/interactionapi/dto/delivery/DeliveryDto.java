package ru.yandex.practicum.commerce.interactionapi.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.commerce.interactionapi.dto.warehouse.AddressDto;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private UUID deliveryId;
    private UUID orderId;
    private AddressDto fromAddress;
    private AddressDto toAddress;
    private Double deliveryWeight;
    private Double deliveryVolume;
    private Boolean fragile;
    private DeliveryState deliveryState;
}
