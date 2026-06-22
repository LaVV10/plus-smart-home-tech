package ru.yandex.practicum.commerce.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "shopping_carts")
@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @Column(name = "username")
    private String username;

    @ElementCollection
    @CollectionTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(name = "username")
    )
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<UUID, Long> products = new HashMap<>();

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public ShoppingCart(String username) {
        this.username = username;
        this.active = true;
        this.products = new HashMap<>();
    }
}
