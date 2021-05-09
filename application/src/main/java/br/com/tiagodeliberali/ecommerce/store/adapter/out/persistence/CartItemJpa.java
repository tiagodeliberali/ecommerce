package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CartItemJpa implements Serializable {
    private UUID productId;
    private float value;
    private String currency;
    private int quantity;
}
