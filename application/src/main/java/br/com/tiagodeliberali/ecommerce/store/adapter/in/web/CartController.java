package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItems;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {
    private final UpdateCartItems updateCartItems;

    @Autowired
    public CartController(UpdateCartItems updateCartItems) {
        this.updateCartItems = updateCartItems;
    }

    @PostMapping(path = "/cart/add/{userId}/{productId}/{quantity}")
    public void addItemToCart(@PathVariable("userId") UUID userId,
                              @PathVariable("productId") UUID productId,
                              @PathVariable("quantity") int quantity) {

        updateCartItems.addItemToCart(new UserId(userId), new ProductId(productId), quantity);

    }
}
