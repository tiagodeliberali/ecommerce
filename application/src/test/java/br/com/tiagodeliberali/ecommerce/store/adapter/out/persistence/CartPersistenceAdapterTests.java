package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
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
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CartPersistenceAdapterTests {
    private final SpringDataStoreRepository springDataStoreRepository;
    private CartPersistenceAdapter adapter;

    @Autowired
    CartPersistenceAdapterTests(SpringDataStoreRepository springDataStoreRepository) {
        this.springDataStoreRepository = springDataStoreRepository;
    }

    @BeforeEach
    void initUseCase() {
        adapter = new CartPersistenceAdapter(springDataStoreRepository);
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
        springDataStoreRepository.save(new CartJpa(UUID.randomUUID(), userId, items));

        Cart cart = adapter.getActiveCart(new UserId(userId));

        assertThat(cart.getUserId().id()).isEqualTo(userId);
        assertThat(cart.getTotalLines()).isEqualTo(1);
        assertThat(cart.getTotalAmount()).isEqualTo(Price.ofDollar(75));
        assertThat(cart.getItemQuantity(new ProductId(productId))).isEqualTo(3);
    }
}
