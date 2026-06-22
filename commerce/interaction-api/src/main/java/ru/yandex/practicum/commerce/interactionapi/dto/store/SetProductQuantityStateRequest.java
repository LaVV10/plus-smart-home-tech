package ru.yandex.practicum.commerce.interactionapi.dto.store;

import jakarta.validation.constraints.NotNull;
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
public class SetProductQuantityStateRequest {

    @NotNull
    private UUID productId;

    @NotNull
    private QuantityState quantityState;
}
