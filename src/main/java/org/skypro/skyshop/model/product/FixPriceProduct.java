package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 666;

    public FixPriceProduct(UUID id, String nameOfProduct) {
        super(id, nameOfProduct);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена: " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
