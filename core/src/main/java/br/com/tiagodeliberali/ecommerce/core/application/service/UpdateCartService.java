package br.com.tiagodeliberali.ecommerce.core.application.service;

import br.com.tiagodeliberali.ecommerce.core.application.port.in.UpdateCartItemUseCase;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartService implements UpdateCartItemUseCase {
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
    public void addItemToCart(UserId userId, ProductId productId, int quantity) throws ProductNotFoundException {
        Cart cart = loadCart.getActiveCart(userId);
        Product product = loadProduct.loadById(productId);

        cart.add(new CartItem(product, quantity));

        updateCart.updateState(cart);
    }
}
