package br.com.tiagodeliberali.ecommerce.application.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CartItemResource {
    @NotNull
    private final UUID productId;

    @NotNull
    private final int quantity;
}
