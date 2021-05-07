package br.com.tiagodeliberali.ecommerce.store.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final UserId userId;
    private final Map<ProductId, CartItem> itemList;

    @Getter
    private Price totalAmount;

    private Cart(UserId userId) {
        this.userId = userId;
        this.totalAmount = Price.ZERO;
        this.itemList = new HashMap<>();
    }

    public static Cart ForUser(UserId userId) {
        return new Cart(userId);
    }

    public void add(CartItem item) {
        if (itemList.containsKey(item.product().getId())) {
            itemList.put(item.product().getId(),
                    item.add(itemList.get(item.product().getId())));
        } else {
            itemList.put(item.product().getId(), item);
        }

        totalAmount = totalAmount.add(item.getTotal());
    }

    public int getTotalLines() {
        return itemList.size();
    }

    public int getItemQuantity(ProductId id) {
        return itemList.get(id).quantity();
    }
}
