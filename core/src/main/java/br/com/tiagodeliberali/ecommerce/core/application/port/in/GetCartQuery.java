package br.com.tiagodeliberali.ecommerce.core.application.port.in;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;

public interface GetCartQuery {
    Cart getActiveCart(UserId userId);
}
