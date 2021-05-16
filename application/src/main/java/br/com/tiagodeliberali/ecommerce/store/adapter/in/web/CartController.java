package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.GetCartQuery;
import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItemUseCase;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class CartController {
    private final UpdateCartItemUseCase updateCartItemUseCase;
    private final GetCartQuery getCartQuery;

    @Autowired
    public CartController(UpdateCartItemUseCase updateCartItemUseCase, GetCartQuery getCartQuery) {
        this.updateCartItemUseCase = updateCartItemUseCase;
        this.getCartQuery = getCartQuery;
    }

    @PostMapping(path = "/cart/add/{userId}")
    public void addItemToCart(@PathVariable("userId") UUID userId,
                              @Valid @RequestBody CartItemResource cartItem) throws ProductNotFoundException {
        updateCartItemUseCase.addItemToCart(
                new UserId(userId),
                new ProductId(cartItem.getProductId()), cartItem.getQuantity());

    }

    @GetMapping(path = "/cart/{userId}")
    public CartResource getCart(@PathVariable("userId") UUID userId) {
        return CartMapper.from(getCartQuery.getActiveCart(new UserId(userId)));
    }
}
