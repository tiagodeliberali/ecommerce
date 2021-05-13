package br.com.tiagodeliberali.ecommerce;

import br.com.tiagodeliberali.ecommerce.store.adapter.in.web.CartItemResource;
import br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence.CartItemJpa;
import br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence.CartJpa;
import br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence.SpringDataStoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateCartItemUseCaseTests {
	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	private final SpringDataStoreRepository springDataStoreRepository;

	@Autowired
	UpdateCartItemUseCaseTests(
			MockMvc mockMvc, ObjectMapper objectMapper, SpringDataStoreRepository springDataStoreRepository) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
		this.springDataStoreRepository = springDataStoreRepository;
	}

	@Test
	void add_first_item_to_cart_saves_to_database() throws Exception {
		// arrange
		UUID userId = UUID.randomUUID();
		UUID productId = UUID.randomUUID();
		CartItemResource cartItem = new CartItemResource(productId, 6);

		// act
		mockMvc.perform(post("/cart/add/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cartItem)))
				.andExpect(status().isOk());

		// assert
		CartJpa cartJpa = springDataStoreRepository.findActiveCartByUser(userId).get();
		List<CartItemJpa> items = cartJpa.getItemList();

		assertThat(cartJpa.getUserId()).isEqualTo(userId);
		assertThat(items.size()).isOne();
		assertThat(items.get(0).getProductId()).isEqualTo(productId);
		assertThat(items.get(0).getCurrency()).isEqualTo("usd");
		assertThat(items.get(0).getValue()).isEqualTo(30);
		assertThat(items.get(0).getQuantity()).isEqualTo(6);
	}

	@Test
	void add_second_item_to_cart_increment_quantity() throws Exception {
		// arrange
		UUID userId = UUID.randomUUID();
		UUID productId = UUID.randomUUID();

		mockMvc.perform(post("/cart/add/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new CartItemResource(productId, 6))))
				.andExpect(status().isOk());

		// act
		mockMvc.perform(post("/cart/add/{userId}", userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new CartItemResource(productId, 3))))
				.andExpect(status().isOk());

		// assert
		CartJpa cartJpa = springDataStoreRepository.findActiveCartByUser(userId).get();
		List<CartItemJpa> items = cartJpa.getItemList();

		assertThat(cartJpa.getUserId()).isEqualTo(userId);
		assertThat(items.size()).isOne();
		assertThat(items.get(0).getProductId()).isEqualTo(productId);
		assertThat(items.get(0).getCurrency()).isEqualTo("usd");
		assertThat(items.get(0).getValue()).isEqualTo(30);
		assertThat(items.get(0).getQuantity()).isEqualTo(9);
	}
}
