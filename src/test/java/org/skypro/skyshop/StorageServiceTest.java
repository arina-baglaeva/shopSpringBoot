package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {
    @Mock
    private final Map<UUID, Product> storageProducts = new HashMap<>();
    @Mock
    private final Map<UUID, Article> storageArticles = new HashMap<>();
    @InjectMocks
    private StorageService storageService;

    @Test
    void whenGetStorageProductsIsNotEmpty_thenReturnsAllProducts() {
        storageService = new StorageService();
        Collection<Product> products = storageService.getStorageProducts();
        Assertions.assertEquals(6, products.size());
    }

    @Test
    void whenGetStorageProductsTryChange_thenReturnsException() {
        storageService = new StorageService();
        Collection<Product> products = storageService.getStorageProducts();
        Product newProduct = new SimpleProduct(UUID.randomUUID(), "Сок", 199);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> products.add(newProduct));
    }

    @Test
    void whenGetStorageArticlesIsNotEmpty_thenReturnsAllArticles() {
        storageService = new StorageService();
        Collection<Article> articles = storageService.getStorageArticles();
        Assertions.assertEquals(6, articles.size());
    }

    @Test
    void whenGetStorageArticlesTryChange_thenReturnsException() {
        storageService = new StorageService();
        Collection<Article> articles = storageService.getStorageArticles();
        Article newArticle = new Article(UUID.randomUUID(), "Сок", "Я люблю Сады Придонья");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> articles.add(newArticle));
    }

    @Test
    void whenGetProductByIdIsExist_thenStorageServiceReturnsOptionalProduct() {
        storageService = new StorageService();
        Product first = storageService.getStorageProducts().iterator().next();
        UUID id = first.getId();
        Optional<Product> result = storageService.getProductById(id);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(first.getName(), result.get().getName());
    }

    @Test
    void whenGetProductByIdIsNotExist_thenStorageServiceReturnsEmpty() {
        storageService = new StorageService();
        UUID notId = UUID.randomUUID();
        Optional<Product> result = storageService.getProductById(notId);
        Assertions.assertFalse(result.isPresent());
    }


}
