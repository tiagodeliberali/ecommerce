package br.com.tiagodeliberali.ecommerce.store.application.port.out;

import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;

public interface LoadCartPort {
    Cart getActiveCart(UserId userId);
}
