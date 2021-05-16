package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemJpa implements Serializable {
    private UUID productId;
    private float value;
    private String currency;
    private int quantity;
}
