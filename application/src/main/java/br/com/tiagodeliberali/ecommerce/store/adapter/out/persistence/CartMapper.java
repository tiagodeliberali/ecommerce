package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.CartId;
import br.com.tiagodeliberali.ecommerce.store.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;

import java.util.ArrayList;
import java.util.Optional;

public class CartMapper {
    private CartMapper() {}

    public static CartJpa fromCart(Cart cart) {
        CartJpa entity = new CartJpa();
        entity.setId(cart.getId().id());
        entity.setUserId(cart.getUserId().id());
        entity.setItemList(new ArrayList<>());
        cart.getItemsIterator().forEachRemaining(item -> entity.getItemList().add(fromCartItem(item)));

        return entity;
    }

    private static CartItemJpa fromCartItem(CartItem item) {
        CartItemJpa result = new CartItemJpa();
        result.setProductId(item.product().getId().id());
        result.setValue(item.product().getValue().amount());
        result.setCurrency(item.product().getValue().currency());
        result.setQuantity(item.quantity());

        return result;
    }

    public static Cart fromCartJpa(CartJpa cart) {
        Cart result = new Cart(new CartId(cart.getId()), new UserId(cart.getUserId()));
        Optional.ofNullable(cart.getItemList()).ifPresent(items -> {
            for (CartItemJpa item: items) {
                result.add(new CartItem(
                        new Product(
                                new ProductId(item.getProductId()),
                                new Price(item.getValue(), item.getCurrency())),
                        item.getQuantity()));
            }
        });

        return result;
    }
}
