package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.core.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductPersistenceAdapterTests {
    private final SpringProductRepository springProductRepository;
    private ProductPersistenceAdapter adapter;

    @Autowired
    ProductPersistenceAdapterTests(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }

    @BeforeEach
    void initUseCase() {
        adapter = new ProductPersistenceAdapter(springProductRepository);
    }

    @Test
    void should_return_product_when_found() throws ProductNotFoundException {
        ProductJpa addedProduct = springProductRepository.save(ProductJpa.build(25, "usd"));

        Product product = adapter.loadById(new ProductId(addedProduct.getId()));

        assertThat(product.getValue().amount()).isEqualTo(25);
        assertThat(product.getValue().currency()).isEqualTo("usd");
    }

    @Test
    void throws_exception_if_not_found() {
        Assertions.assertThrows(
                ProductNotFoundException.class, () -> adapter.loadById(new ProductId(UUID.randomUUID())));
    }
}
