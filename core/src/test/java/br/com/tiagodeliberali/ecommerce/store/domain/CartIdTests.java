package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartIdTests {
    @Test
    void cart_id_cannot_accept_null_uuid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CartId(null));
    }
}
