package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import org.springframework.stereotype.Component;

@Component
public class ProductPersitenceAdapter implements LoadProductPort {
    @Override
    public Product loadById(ProductId productId) {
        return new Product(productId, Price.ofDollar(30));
    }
}
