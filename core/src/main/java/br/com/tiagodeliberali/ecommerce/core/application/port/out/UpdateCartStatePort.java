package br.com.tiagodeliberali.ecommerce.core.application.port.out;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;

public interface UpdateCartStatePort {
    void updateState(Cart cart);
}
