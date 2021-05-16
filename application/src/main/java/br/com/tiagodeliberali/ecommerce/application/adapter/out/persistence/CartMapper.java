package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.CartId;
import br.com.tiagodeliberali.ecommerce.core.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.core.domain.Price;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;

import java.util.ArrayList;
import java.util.Optional;

public class CartMapper {
    private CartMapper() {
    }

    public static CartJpa from(Cart cart) {
        CartJpa entity = new CartJpa();
        entity.setId(cart.getId().id());
        entity.setUserId(cart.getUserId().id());
        entity.setItemList(new ArrayList<>());
        cart.getItemsIterator().forEachRemaining(item -> entity.getItemList().add(from(item)));

        return entity;
    }

    private static CartItemJpa from(CartItem item) {
        CartItemJpa result = new CartItemJpa();
        result.setProductId(item.product().getId().id());
        result.setValue(item.product().getValue().amount());
        result.setCurrency(item.product().getValue().currency());
        result.setQuantity(item.quantity());

        return result;
    }

    public static Cart from(CartJpa cart) {
        Cart result = new Cart(new CartId(cart.getId()), new UserId(cart.getUserId()));
        Optional.ofNullable(cart.getItemList()).ifPresent(items -> {
            for (CartItemJpa item : items) {
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
