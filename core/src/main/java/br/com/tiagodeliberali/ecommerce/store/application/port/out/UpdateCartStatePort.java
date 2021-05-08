package br.com.tiagodeliberali.ecommerce.store.application.port.out;

import br.com.tiagodeliberali.ecommerce.store.domain.Cart;

public interface UpdateCartStatePort {
    void updateState(Cart cart);
}
