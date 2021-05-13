package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemJpa implements Serializable {
    private UUID productId;
    private float value;
    private String currency;
    private int quantity;
}
