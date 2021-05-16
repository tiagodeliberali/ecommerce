package br.com.tiagodeliberali.ecommerce.core.application.port.out;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;

public interface LoadCartPort {
    Cart getActiveCart(UserId userId);
}
