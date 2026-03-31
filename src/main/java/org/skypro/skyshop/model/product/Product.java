package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String nameOfProduct;

    public Product(UUID id, String nameOfProduct) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
    }

    public UUID getId() {
        return id;
    }

    public abstract int getPrice();

    public String getName() {
        return nameOfProduct;
    }

    public int hashCode() {
        return Objects.hash(nameOfProduct);
    }

    public boolean isSpecial() {
        return false;
    }

    @JsonIgnore
    public String searchTerm() {
        return nameOfProduct;
    }

    @JsonIgnore
    public String getOfTypeContent() {
        return "PRODUCT";
    }

}
