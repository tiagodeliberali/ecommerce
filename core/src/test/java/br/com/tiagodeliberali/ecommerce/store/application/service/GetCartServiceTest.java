package br.com.tiagodeliberali.ecommerce.store.application.service;

import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.CartId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCartServiceTest {
    @Mock
    private LoadCartPort loadCart;

    @InjectMocks
    private GetCartService getCartService;

    @Test
    void get_cart_from_user_id() {
        UserId userId = new UserId(UUID.randomUUID());
        when(loadCart.getActiveCart(userId)).thenReturn(new Cart(new CartId(UUID.randomUUID()), userId));

        Cart cart = getCartService.getActiveCart(userId);

        assertThat(cart.getUserId()).isEqualTo(userId);
    }
}
