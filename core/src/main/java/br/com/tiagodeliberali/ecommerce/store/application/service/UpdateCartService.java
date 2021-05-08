package br.com.tiagodeliberali.ecommerce.store.application.service;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItems;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCartService implements UpdateCartItems {
    private final LoadCartPort loadCart;
    private final UpdateCartStatePort updateCart;
    private final LoadProductPort loadProduct;

    @Autowired
    public UpdateCartService(LoadCartPort loadCart, UpdateCartStatePort updateCart, LoadProductPort loadProduct) {
        this.loadCart = loadCart;
        this.loadProduct = loadProduct;
        this.updateCart = updateCart;
    }

    @Override
    public void addItemToCart(UserId userId, ProductId productId, int quantity) {
        Cart cart = loadCart.loadFromUser(userId);
        Product product = loadProduct.loadById(productId);

        cart.add(new CartItem(product, quantity));

        updateCart.updateState(cart);
    }
}
