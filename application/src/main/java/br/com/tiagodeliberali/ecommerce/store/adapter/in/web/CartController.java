package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItemUseCase;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {
    private final UpdateCartItemUseCase updateCartItemUseCase;

    @Autowired
    public CartController(UpdateCartItemUseCase updateCartItemUseCase) {
        this.updateCartItemUseCase = updateCartItemUseCase;
    }

    @PostMapping(path = "/cart/add/{userId}/{productId}/{quantity}")
    public void addItemToCart(@PathVariable("userId") UUID userId,
                              @PathVariable("productId") UUID productId,
                              @PathVariable("quantity") int quantity) {

        updateCartItemUseCase.addItemToCart(new UserId(userId), new ProductId(productId), quantity);

    }
}
