package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;

public class ProductMapper {
    private ProductMapper() {
    }

    public static Product from(ProductJpa product) {
        return new Product(new ProductId(product.getId()), new Price(product.getPrice(), product.getCurrency()));
    }

    public static ProductJpa from(Product product) {
        return new ProductJpa(
                product.getId().id(),
                product.getValue().amount(),
                product.getValue().currency());
    }
}
