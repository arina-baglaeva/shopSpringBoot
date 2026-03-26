package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String nameOfArticle;
    private final String text;


    public Article(UUID id, String name, String text) {
        this.id = id;
        this.nameOfArticle = name;
        this.text = text;
    }

    @Override
    public UUID getId(){return id;}

    @Override
    public String toString() {
        return "Название статьи: " + nameOfArticle + ". Текст статьи: " + text;
    }
    @JsonIgnore
    @Override
    public String searchTerm() {
        return nameOfArticle + " " + text;
    }
    @JsonIgnore
    @Override
    public String getOfTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return nameOfArticle;
    }
}
