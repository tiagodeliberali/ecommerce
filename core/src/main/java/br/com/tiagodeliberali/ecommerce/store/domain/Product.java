package br.com.tiagodeliberali.ecommerce.store.domain;

import lombok.Getter;

import java.util.Objects;

public class Product {
    @Getter
    private final ProductId id;

    @Getter
    private final Price value;

    public Product(ProductId id, Price value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
