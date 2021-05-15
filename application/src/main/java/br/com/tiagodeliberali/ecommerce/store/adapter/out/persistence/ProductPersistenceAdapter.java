package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements LoadProductPort {
    private final SpringProductRepository productRepository;

    @Autowired
    public ProductPersistenceAdapter(SpringProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product loadById(ProductId productId) throws ProductNotFoundException {
        return productRepository
                .findById(productId.id())
                .map(ProductMapper::from)
                .orElseThrow(() -> new ProductNotFoundException(productId.id()));
    }
}
