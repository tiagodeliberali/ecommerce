package br.com.tiagodeliberali.ecommerce.store.application.port.in;

import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;

public interface UpdateCartItems {
    void addItemToCart(UserId userId, ProductId productId, int quantity);
}
