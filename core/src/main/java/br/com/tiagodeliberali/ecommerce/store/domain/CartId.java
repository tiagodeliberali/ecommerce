package br.com.tiagodeliberali.ecommerce.store.domain;

import java.util.UUID;

public record CartId(UUID id) {
    public CartId {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
    }
}
