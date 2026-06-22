package ru.yandex.practicum.commerce.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "warehouse_products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseProduct {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "fragile", nullable = false)
    private Boolean fragile;

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "depth", nullable = false)
    private Double depth;

    @Column(name = "weight", nullable = false)
    private Double weight;
}
