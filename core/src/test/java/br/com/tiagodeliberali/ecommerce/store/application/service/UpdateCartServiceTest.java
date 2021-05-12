package br.com.tiagodeliberali.ecommerce.store.application.service;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadProductPort;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.UpdateCartStatePort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.CartId;
import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCartServiceTest {
    @Mock
    private LoadCartPort loadCart;

    @Mock
    private UpdateCartStatePort updateCart;

    @Mock
    private LoadProductPort loadProduct;

    @InjectMocks
    private UpdateCartService updateCartService;

    @Test
    public void add_item_to_cart_persist_loaded_cart_and_product() {
        UserId userId = new UserId(UUID.randomUUID());
        ProductId productId = new ProductId(UUID.randomUUID());

        when(loadCart.getActiveCart(userId)).thenReturn(new Cart(new CartId(UUID.randomUUID()), userId));
        when(loadProduct.loadById(productId)).thenReturn(new Product(productId, Price.ofDollar(10)));

        updateCartService.addItemToCart(userId, productId, 4);

        verify(loadCart).getActiveCart(userId);
        verify(loadProduct).loadById(productId);
        verify(updateCart).updateState(any(Cart.class));
    }
}
