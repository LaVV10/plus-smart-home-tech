package ru.yandex.practicum.commerce.interactionapi.dto.store;

import jakarta.validation.constraints.NotBlank;
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
public class ProductDto {

    private UUID productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    private String imageSrc;

    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private QuantityState quantityState;

    @NotNull
    private ProductState productState;
}
