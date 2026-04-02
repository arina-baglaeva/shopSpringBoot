package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ProductBasketTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;


    @Test
    void whenAddProductIsNotExist_thenReturnException() {
        UUID id = UUID.randomUUID();
        Mockito.doReturn(Optional.empty()).when(storageService).getProductById(id);
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void whenAddProductIsExist_thenAddProduct() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "Сок", 199);
        Mockito.doReturn(Optional.of(product)).when(storageService).getProductById(id);
        basketService.addProduct(id);
        Mockito.verify(productBasket).addToBasket(id);
    }

    @Test
    void whenGetUserBasketIsEmpty_thenReturnEmptyBasket() {
        Mockito.doReturn(Collections.emptyMap()).when(productBasket).getAllProducts();
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertTrue(userBasket.getBasketItem().isEmpty());
        Assertions.assertEquals(0, userBasket.getTotal());
    }

    @Test
    void whenGetUserBasketSuitable_thenReturnSuitableBasket() {
        Product pr1 = new SimpleProduct(UUID.randomUUID(), "Яблоко", 30);
        Product pr2 = new SimpleProduct(UUID.randomUUID(), "Огурец", 20);
        Map<UUID, Integer> map = new HashMap<>();
        map.put(pr1.getId(), 3);
        map.put(pr2.getId(), 2);
        int sum = pr1.getPrice() * 3 + pr2.getPrice() * 2;
        Mockito.doReturn(map).when(productBasket).getAllProducts();
        Mockito.doReturn(Optional.of(pr1)).when(storageService).getProductById(pr1.getId());
        Mockito.doReturn(Optional.of(pr2)).when(storageService).getProductById(pr2.getId());
        UserBasket userBasket = basketService.getUserBasket();
        List<BasketItem> items = userBasket.getBasketItem();
        Assertions.assertEquals(2, items.size());

        Assertions.assertTrue(items.stream().anyMatch(item -> item.getProduct().getId().equals(pr1.getId()) && item.getCount() == 3));
        Assertions.assertTrue(items.stream().anyMatch(item -> item.getProduct().getId().equals(pr2.getId()) && item.getCount() == 2));
        Assertions.assertEquals(sum, userBasket.getTotal());
    }
}
