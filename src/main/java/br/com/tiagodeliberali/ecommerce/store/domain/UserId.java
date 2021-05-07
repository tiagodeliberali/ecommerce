package br.com.tiagodeliberali.ecommerce.store.domain;

import java.util.UUID;

public record UserId(UUID id) {
    public UserId {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
    }
}
