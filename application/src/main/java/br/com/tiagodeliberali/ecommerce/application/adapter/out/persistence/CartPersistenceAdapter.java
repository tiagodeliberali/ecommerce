package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.core.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartPersistenceAdapter implements LoadCartPort, UpdateCartStatePort {
    private final SpringCartRepository storeRepository;

    @Autowired
    public CartPersistenceAdapter(SpringCartRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Cart getActiveCart(UserId userId) {
        CartJpa cart = storeRepository
                .findActiveCartByUser(userId.id())
                .orElseGet(() -> {
                    CartJpa newCart = new CartJpa();
                    newCart.setUserId(userId.id());
                    return storeRepository.save(newCart);
                });

        return CartMapper.from(cart);
    }

    @Override
    public void updateState(Cart cart) {
        CartJpa entity = CartMapper.from(cart);
        storeRepository.save(entity);
    }
}
