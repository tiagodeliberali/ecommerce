package br.com.tiagodeliberali.ecommerce.core.application.port.out;

import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;

public interface LoadProductPort {
    Product loadById(ProductId productId) throws ProductNotFoundException;
}
