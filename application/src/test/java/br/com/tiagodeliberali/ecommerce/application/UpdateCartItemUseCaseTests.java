package br.com.tiagodeliberali.ecommerce.application;

import br.com.tiagodeliberali.ecommerce.application.adapter.in.web.CartItemResource;
import br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence.CartItemJpa;
import br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence.CartJpa;
import br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence.ProductJpa;
import br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence.SpringCartRepository;
import br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence.SpringProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateCartItemUseCaseTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final SpringCartRepository springCartRepository;
    private final SpringProductRepository springProductRepository;

    @Autowired
    UpdateCartItemUseCaseTests(
            MockMvc mockMvc,
            ObjectMapper objectMapper,
            SpringCartRepository springCartRepository,
            SpringProductRepository springProductRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.springCartRepository = springCartRepository;
        this.springProductRepository = springProductRepository;
    }

    @Test
    void add_first_item_to_cart_saves_to_database() throws Exception {
        // arrange
        UUID userId = UUID.randomUUID();
        ProductJpa addedProduct = springProductRepository.save(ProductJpa.build(30, "usd"));
        CartItemResource cartItem = new CartItemResource(addedProduct.getId(), 6);

        // act
        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());

        // assert
        CartJpa cartJpa = springCartRepository.findActiveCartByUser(userId).get();
        List<CartItemJpa> items = cartJpa.getItemList();

        assertThat(cartJpa.getUserId()).isEqualTo(userId);
        assertThat(items.size()).isOne();
        assertThat(items.get(0).getProductId()).isEqualTo(addedProduct.getId());
        assertThat(items.get(0).getCurrency()).isEqualTo("usd");
        assertThat(items.get(0).getValue()).isEqualTo(30);
        assertThat(items.get(0).getQuantity()).isEqualTo(6);
    }

    @Test
    void get_cart_from_user() throws Exception {
        // arrange
        UUID userId = UUID.randomUUID();
        ProductJpa addedProduct = springProductRepository.save(ProductJpa.build(20, "usd"));

        mockMvc.perform(post("/cart/add/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CartItemResource(addedProduct.getId(), 6))))
                .andExpect(status().isOk());

        // act
        MvcResult result = mockMvc.perform(get("/cart/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // assert
        assertThat(result.getResponse().getContentAsString())
                .contains("\"userId\":\"" + userId + "\"")
                .contains("\"totalLines\":1")
                .contains("\"totalAmount\":120.0")
                .contains("\"amount\":20.0")
                .contains("\"currency\":\"usd\"")
                .contains("\"quantity\":6");
    }
}
