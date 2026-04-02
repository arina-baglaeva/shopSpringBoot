package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @BeforeEach

    @Test
    void whenStorageServiceIsEmpty_thenReturnsEmptyList() {
        Mockito.doReturn(Collections.emptyList()).when(storageService).getStorageProducts();
        Mockito.doReturn(Collections.emptyList()).when(storageService).getStorageArticles();
        Collection<SearchResult> result = searchService.search("fhfhf");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void whenStorageServiceNoMatch_ThenReturnEmptyList() {
        Product testproduct = new SimpleProduct(UUID.randomUUID(), "Сосиска", 150);
        Mockito.doReturn(List.of(testproduct)).when(storageService).getStorageProducts();
        Mockito.doReturn(Collections.emptyList()).when(storageService).getStorageArticles();
        Collection<SearchResult> result = searchService.search("fhfhf");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void whenStorageServiceYesMatch_ThenReturnList() {
        Product testproduct = new SimpleProduct(UUID.randomUUID(), "Яблоко", 60);
        Mockito.doReturn(List.of(testproduct)).when(storageService).getStorageProducts();
        Mockito.doReturn(Collections.emptyList()).when(storageService).getStorageArticles();
        Collection<SearchResult> result = searchService.search("Яблоко");
        Assertions.assertEquals(1, result.size());
        SearchResult found = result.iterator().next();
        Assertions.assertEquals("Яблоко", found.getName());
    }

}
