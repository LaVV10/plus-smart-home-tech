package ru.yandex.practicum.commerce.interactionapi.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookedProductsDto {

    private Double deliveryWeight;

    private Double deliveryVolume;

    private Boolean fragile;
}
