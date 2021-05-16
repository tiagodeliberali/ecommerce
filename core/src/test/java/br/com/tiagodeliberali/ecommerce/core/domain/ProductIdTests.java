package br.com.tiagodeliberali.ecommerce.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductIdTests {
    @Test
    void product_id_cannot_accept_null_uuid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductId(null));
    }
}
