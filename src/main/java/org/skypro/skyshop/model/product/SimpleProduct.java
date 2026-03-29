package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String nameOfProduct, int price) {
        super(id, nameOfProduct);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0! ");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ":" + getPrice();
    }
}
