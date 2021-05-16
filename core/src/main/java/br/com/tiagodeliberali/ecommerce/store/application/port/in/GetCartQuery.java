package br.com.tiagodeliberali.ecommerce.store.application.port.in;

import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;

public interface GetCartQuery {
    Cart getActiveCart(UserId userId);
}
