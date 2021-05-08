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
        ProductId itemId = item.product().getId();

        if (itemList.containsKey(itemId)) {
            CartItem currentItem = itemList.get(itemId);
            itemList.put(itemId, currentItem.add(item));
        } else {
            itemList.put(itemId, item);
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
