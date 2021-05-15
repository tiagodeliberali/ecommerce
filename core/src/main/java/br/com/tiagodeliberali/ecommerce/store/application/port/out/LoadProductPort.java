package br.com.tiagodeliberali.ecommerce.store.application.port.out;

import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;

public interface LoadProductPort {
    Product loadById(ProductId productId) throws ProductNotFoundException;
}
