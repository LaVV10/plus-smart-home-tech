package ru.yandex.practicum.commerce.interactionapi.dto.warehouse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyProductForOrderRequest {

    @NotNull
    private UUID orderId;

    @NotEmpty
    private Map<UUID, Long> products = new HashMap<>();
}
