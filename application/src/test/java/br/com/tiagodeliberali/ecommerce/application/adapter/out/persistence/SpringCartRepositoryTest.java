package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringCartRepositoryTest {
    private final SpringCartRepository springCartRepository;

    @Autowired
    SpringCartRepositoryTest(SpringCartRepository springCartRepository) {
        this.springCartRepository = springCartRepository;
    }

    @Test
    void return_empty_when_cart_do_not_exists() {
        UUID userId = UUID.randomUUID();
        Optional<CartJpa> cart = springCartRepository.findActiveCartByUser(userId);

        assertThat(cart).isEmpty();
    }

    @Test
    void return_active_cart_when_found() {
        UUID userId = UUID.randomUUID();
        springCartRepository.save(new CartJpa(UUID.randomUUID(), userId, null));

        Optional<CartJpa> cart = springCartRepository.findActiveCartByUser(userId);

        assertThat(cart).isNotEmpty();
        assertThat(cart.get().getUserId()).isEqualTo(userId);
    }
}
