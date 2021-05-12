package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItemUseCase;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class CartController {
    private final UpdateCartItemUseCase updateCartItemUseCase;

    @Autowired
    public CartController(UpdateCartItemUseCase updateCartItemUseCase) {
        this.updateCartItemUseCase = updateCartItemUseCase;
    }

    @PostMapping(path = "/cart/add/{userId}")
    public void addItemToCart(@PathVariable("userId") UUID userId,
                              @Valid @RequestBody CartItemResource cartItem) {

        updateCartItemUseCase.addItemToCart(
                new UserId(userId), new ProductId(cartItem.getProductId()), cartItem.getQuantity());

    }
}
