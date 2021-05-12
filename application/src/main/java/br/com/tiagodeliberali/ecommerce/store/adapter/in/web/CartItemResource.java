package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CartItemResource {
    @NotNull
    private UUID productId;

    @NotNull
    private int quantity;
}
