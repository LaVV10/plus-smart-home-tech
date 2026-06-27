package ru.yandex.practicum.commerce.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddress {

    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
}
