package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.GetCartQuery;
import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItemUseCase;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.CartId;
import br.com.tiagodeliberali.ecommerce.store.domain.CartItem;
import br.com.tiagodeliberali.ecommerce.store.domain.Price;
import br.com.tiagodeliberali.ecommerce.store.domain.Product;
import br.com.tiagodeliberali.ecommerce.store.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static br.com.tiagodeliberali.ecommerce.store.adapter.in.web.ResponseBodyMatchers.responseBody;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UpdateCartItemUseCase updateCartItemUseCase;

    @MockBean
    private GetCartQuery getCartQuery;

    @Test
    void add_cart_item_on_valid_input() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        CartItemResource cartItem = new CartItemResource(productId, 6);

        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());

        verify(updateCartItemUseCase).addItemToCart(new UserId(userId), new ProductId(productId), 6);
    }

    @Test
    void missing_information_from_resource_returns_4xx_and_details() throws Exception {
        UUID userId = UUID.randomUUID();
        CartItemResource cartItem = new CartItemResource(null, 6);

        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError(
                        "invalid-input-fields",
                        "Input data is invalid",
                        "Please fix the field values: productId: must not be null"));
    }

    @Test
    void product_not_found_returns_4xx_and_details() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        CartItemResource cartItem = new CartItemResource(productId, 6);

        doThrow(new ProductNotFoundException(productId))
                .when(updateCartItemUseCase)
                .addItemToCart(new UserId(userId), new ProductId(productId), 6);

        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError(
                        "product-not-found",
                        "Product id supplied was not found",
                        String.format("Product with id %s was not found", productId)));
    }

    @Test
    void load_new_user_cart() throws Exception {
        UUID userId = UUID.randomUUID();
        Cart cart = buildCart(userId);
        when(getCartQuery.getActiveCart(new UserId(userId))).thenReturn(cart);

        mockMvc.perform(get("/cart/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(responseBody().containsObjectAsJson(CartMapper.from(cart)))
                .andReturn();
    }

    private Cart buildCart(UUID userId) {
        Cart cart = new Cart(new CartId(UUID.randomUUID()), new UserId(userId));
        cart.add(new CartItem(new Product(new ProductId(UUID.randomUUID()), Price.ofDollar(30)), 10));
        return cart;
    }
}
