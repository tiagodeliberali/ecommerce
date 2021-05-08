package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class CartPersistenceAdapter implements LoadCartPort, UpdateCartStatePort {
    @Override
    public Cart loadFromUser(UserId userId) {
        return Cart.ForUser(userId);
    }

    @Override
    public void updateState(Cart cart) {

    }
}
