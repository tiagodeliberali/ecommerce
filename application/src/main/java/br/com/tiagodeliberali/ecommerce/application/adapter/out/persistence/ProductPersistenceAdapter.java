package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.core.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
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
