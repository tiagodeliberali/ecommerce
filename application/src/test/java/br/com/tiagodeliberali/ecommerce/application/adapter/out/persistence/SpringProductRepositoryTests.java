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
class SpringProductRepositoryTests {
    private final SpringProductRepository springProductRepository;

    @Autowired
    SpringProductRepositoryTests(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }

    @Test
    void return_empty_when_product_not_found() {
        UUID productId = UUID.randomUUID();
        Optional<ProductJpa> product = springProductRepository.findById(productId);

        assertThat(product).isEmpty();
    }

    @Test
    void return_product_when_found() {
        ProductJpa addedProduct = springProductRepository.save(ProductJpa.build(30, "usd"));

        Optional<ProductJpa> product = springProductRepository.findById(addedProduct.getId());

        assertThat(product).isNotEmpty();
        assertThat(product.get().getPrice()).isEqualTo(30);
        assertThat(product.get().getCurrency()).isEqualTo("usd");
    }
}
