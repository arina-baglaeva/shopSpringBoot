package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> mapOfProduct;

    public ProductBasket() {
        this.mapOfProduct = new HashMap<>();
    }

    public void addToBasket(UUID id) {
        Integer current = mapOfProduct.get(id);
        if (current == null) {
            mapOfProduct.put(id, 1);
        } else {
            mapOfProduct.put(id, current + 1);
        }
    }

    public Map<UUID, Integer> getAllProducts() {
        return Collections.unmodifiableMap(mapOfProduct);
    }
}
