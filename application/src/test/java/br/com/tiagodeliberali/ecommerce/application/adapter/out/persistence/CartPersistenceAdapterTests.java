package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.CartId;
import br.com.tiagodeliberali.ecommerce.core.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.core.domain.Price;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartPersistenceAdapterTests {
    private final SpringCartRepository springCartRepository;
    private CartPersistenceAdapter adapter;

    @Autowired
    CartPersistenceAdapterTests(SpringCartRepository springCartRepository) {
        this.springCartRepository = springCartRepository;
    }

    @BeforeEach
    void initUseCase() {
        adapter = new CartPersistenceAdapter(springCartRepository);
    }

    @Test
    void create_cart_when_not_found() {
        UserId userId = new UserId(UUID.randomUUID());

        Cart cart = adapter.getActiveCart(userId);

        assertThat(cart.getUserId()).isEqualTo(userId);
        assertThat(cart.getTotalLines()).isZero();
        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(0));
    }

    @Test
    void return_cart_when_exists() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        List<CartItemJpa> items =
                Collections.singletonList(new CartItemJpa(productId, 25, "usd", 3));
        springCartRepository.save(new CartJpa(UUID.randomUUID(), userId, items));

        Cart cart = adapter.getActiveCart(new UserId(userId));

        assertThat(cart.getUserId().id()).isEqualTo(userId);
        assertThat(cart.getTotalLines()).isEqualTo(1);
        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(75));
        assertThat(cart.getItemQuantity(new ProductId(productId))).isEqualTo(3);
    }

    @Test
    void persist_cart() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();
        Cart cart = new Cart(new CartId(cartId), new UserId(userId));
        cart.add(new CartItem(new Product(new ProductId(productId), Price.ofDollar(50)), 5));

        adapter.updateState(cart);

        CartJpa cartJpa = springCartRepository.findActiveCartByUser(userId).get();
        List<CartItemJpa> items = cartJpa.getItemList();

        assertThat(cartJpa.getUserId()).isEqualTo(userId);
        assertThat(items.size()).isOne();
        assertThat(items.get(0).getProductId()).isEqualTo(productId);
        assertThat(items.get(0).getCurrency()).isEqualTo("usd");
        assertThat(items.get(0).getValue()).isEqualTo(50);
        assertThat(items.get(0).getQuantity()).isEqualTo(5);
    }
}
