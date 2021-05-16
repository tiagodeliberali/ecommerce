package br.com.tiagodeliberali.ecommerce.core;

import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.Product;
import org.assertj.core.api.AbstractAssert;

public class CartAssert extends AbstractAssert<CartAssert, Cart> {
    CartAssert(Cart cart) {
        super(cart, CartAssert.class);
    }

    public static CartAssert assertThat(Cart actual) {
        return new CartAssert(actual);
    }

    public CartAssert hasItemWithQuantity(Product product, int quantity) {
        int foundQuantity = actual.getItemQuantity(product.getId());
        if (foundQuantity != quantity) {
            failWithMessage(String.format("Expected product %s with quantity %d but found %d.",
                    product.getId().id(), quantity, foundQuantity));
        }
        return this;
    }
}
