package ru.yandex.practicum.commerce.interactionapi.dto.warehouse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NewProductInWarehouseRequest {

    @NotNull
    private UUID productId;

    private boolean fragile;

    @Valid
    @NotNull
    private DimensionDto dimension;

    @NotNull
    @Positive
    private Double weight;
}
