package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.UpdateCartItemUseCase;
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

import static org.mockito.Mockito.verify;
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
    void missing_information_from_resource_returns_4xx() throws Exception {
        UUID userId = UUID.randomUUID();
        CartItemResource cartItem = new CartItemResource(null, 6);

        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().is4xxClientError());
    }
}