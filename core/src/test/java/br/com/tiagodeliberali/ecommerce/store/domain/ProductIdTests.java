package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductIdTests {
    @Test
    public void product_id_cannot_accept_null_uuid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new ProductId(null);
        });
    }
}
