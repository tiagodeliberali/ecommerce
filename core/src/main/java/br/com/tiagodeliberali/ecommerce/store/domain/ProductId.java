package br.com.tiagodeliberali.ecommerce.store.domain;

import java.util.UUID;

public record ProductId(UUID id) {
    public ProductId {
        if (id == null) {
            throw new IllegalArgumentException("id should not be null");
        }
    }
}
