package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartPersistenceAdapter implements LoadCartPort, UpdateCartStatePort {
    private final SpringDataStoreRepository storeRepository;

    @Autowired
    public CartPersistenceAdapter(SpringDataStoreRepository storeRepository) {
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

        return CartMapper.FromCartJpa(cart);
    }

    @Override
    public void updateState(Cart cart) {
        CartJpa entity = CartMapper.FromCart(cart);
        storeRepository.save(entity);
    }
}
