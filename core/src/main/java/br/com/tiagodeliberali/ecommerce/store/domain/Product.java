package br.com.tiagodeliberali.ecommerce.store.domain;

import lombok.Getter;

public class Product {
    @Getter
    private final ProductId id;

    @Getter
    private final Price value;

    public Product(ProductId id, Price value) {
        this.id = id;
        this.value = value;
    }
}
