package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class SpringDataStoreRepositoryTest {
    private final SpringDataStoreRepository springDataStoreRepository;

    @Autowired
    SpringDataStoreRepositoryTest(SpringDataStoreRepository springDataStoreRepository) {
        this.springDataStoreRepository = springDataStoreRepository;
    }

    @Test
    void return_empty_when_cart_do_not_exists() {
        UUID userId = UUID.randomUUID();
        Optional<CartJpa> cart = springDataStoreRepository.findActiveCartByUser(userId);

        assertThat(cart).isEmpty();
    }

    @Test
    void return_active_cart_when_found() {
        UUID userId = UUID.randomUUID();
        springDataStoreRepository.save(new CartJpa(UUID.randomUUID(), userId, null));

        Optional<CartJpa> cart = springDataStoreRepository.findActiveCartByUser(userId);

        assertThat(cart).isNotEmpty();
        assertThat(cart.get().getUserId()).isEqualTo(userId);
    }
}
