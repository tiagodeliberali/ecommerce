package br.com.tiagodeliberali.ecommerce.store.application.port.out;

import java.util.UUID;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(UUID id) {
        super(String.format("Product with id %s was not found", id));
    }
}
