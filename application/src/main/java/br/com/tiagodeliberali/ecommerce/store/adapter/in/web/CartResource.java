package br.com.tiagodeliberali.ecommerce.store.adapter.in.web;

import lombok.Value;

import java.util.List;

@Value
public class CartResource {
    String userId;
    int totalLines;
    float totalAmount;
    String currency;
    List<CartItem> items;

    @Value
    static
    class CartItem {
        String productId;
        float amount;
        String currency;
        int quantity;
        float totalAmount;
    }
}
