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
public class AddressDto {

    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
}
