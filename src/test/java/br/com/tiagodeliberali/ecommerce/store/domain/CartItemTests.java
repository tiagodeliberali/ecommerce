package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

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

    @Test
    public void cart_item_returns_correct_total_amount() {
        Product product = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));
        CartItem item = new CartItem(product, 3);

        Price total = item.getTotal();

        assertThat(total).isEqualTo(Price.ofDollar(45));
    }
}
