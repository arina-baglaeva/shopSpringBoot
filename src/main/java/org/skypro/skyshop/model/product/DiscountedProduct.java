package org.skypro.skyshop.model.product;

import java.util.Random;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basicPrice;
    private final int sale;

    public DiscountedProduct(UUID id, String nameOfProducts, int basicPrice) {
        super(id, nameOfProducts);
        Random r = new Random();
        int sale = r.nextInt(0, 100);
        if (basicPrice < 0 || sale < 0 || sale > 100) {
            throw new IllegalArgumentException("Базовая цена должна быть строго больше 0. Процент скидки должен быть числом в диапазоне от 0 до 100 включительно.");
        }
        this.basicPrice = basicPrice;
        this.sale = sale;
    }

    @Override
    public int getPrice() {
        return basicPrice - (basicPrice * sale / 100);
    }

    @Override
    public String toString() {
        return getName() + ":" + getPrice() + " (" + sale + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
