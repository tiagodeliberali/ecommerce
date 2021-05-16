package br.com.tiagodeliberali.ecommerce.application.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    private CartMapper() {}

    public static CartResource from(Cart cart) {
        List<CartResource.CartItem> items = new ArrayList<>();

        cart.getItemsIterator().forEachRemaining(item -> items.add(new CartResource.CartItem(
                item.product().getId().id().toString(),
                item.product().getValue().amount(),
                item.product().getValue().currency(),
                item.quantity(),
                item.getTotal().amount())));

        return new CartResource(
                cart.getUserId().id().toString(),
                cart.getTotalLines(),
                cart.getTotalAmount().amount(),
                cart.getTotalAmount().currency(),
                items);
    }
}
