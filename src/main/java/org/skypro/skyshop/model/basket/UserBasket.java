package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItem;
    private final int total;

    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = basketItem;
        this.total = calculateTotal(basketItem);
    }
    public int calculateTotal( List<BasketItem> basketItem){
        return basketItem.stream().
                mapToInt(item -> item.getProduct().getPrice() * item.getCount()).sum();
    }
    public List<BasketItem> getBasketItem() {
        return this.basketItem;
    }

    public int getTotal() {
        return total;
    }
}
