package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.concat(
                storageService.getStorageArticles().stream().map(a -> (Searchable) a),
                storageService.getStorageProducts().stream().map(p -> (Searchable) p)
        ).collect(Collectors.toList());
    }

    public Collection<SearchResult> search(String s) {
        return getAllSearchable().stream().filter(p -> p.searchTerm().toLowerCase().contains(s.toLowerCase())).map(SearchResult::fromSearchable).collect(Collectors.toList());
    }
}
