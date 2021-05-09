package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

public class CartTests {
    @Test
    public void add_item_to_cart_updates_total_amount() {
        Cart cart = createEmptyCart();
        Product product = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));

        cart.add(new CartItem(product, 2));

        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(30));
        assertThat(cart.getTotalLines()).isEqualTo(1);
        assertThat(cart.getItemQuantity(product.getId())).isEqualTo(2);
    }

    @Test
    public void add_same_item_twice_do_not_include_new_line() {
        Cart cart = createEmptyCart();
        Product product = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));

        cart.add(new CartItem(product, 2));
        cart.add(new CartItem(product, 3));

        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(75));
        assertThat(cart.getTotalLines()).isEqualTo(1);
        assertThat(cart.getItemQuantity(product.getId())).isEqualTo(5);
    }

    @Test
    public void add_different_items_include_new_lines() {
        Cart cart = createEmptyCart();
        Product product1 = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(15));
        cart.add(new CartItem(product1, 2));

        Product product2 = new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(20));
        cart.add(new CartItem(product2, 3));

        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(90));
        assertThat(cart.getTotalLines()).isEqualTo(2);
        assertThat(cart.getItemQuantity(product1.getId())).isEqualTo(2);
        assertThat(cart.getItemQuantity(product2.getId())).isEqualTo(3);
    }

    private Cart createEmptyCart() {
        return new Cart(new CartId(UUID.randomUUID()), new UserId(UUID.randomUUID()));
    }
}
