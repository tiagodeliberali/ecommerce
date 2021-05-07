package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CartItemTests {
    @Test
    public void cart_item_do_not_accept_null_product() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(null, 3);
        });
    }

    @Test
    public void cart_item_do_not_accept_zero_quantity() {
        Product product = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, 0);
        });
    }

    @Test
    public void cart_item_do_not_accept_negative_quantity() {
        Product product = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, -2);
        });
    }
}
