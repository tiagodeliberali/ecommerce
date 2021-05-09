package br.com.tiagodeliberali.ecommerce.store.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {
    @Getter
    private CartId id;

    @Getter
    private final UserId userId;

    @Getter
    private Price totalAmount;

    private final Map<ProductId, CartItem> itemList;

    public Cart(CartId id, UserId userId) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = Price.ZERO;
        this.itemList = new HashMap<>();
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

    public Iterator<CartItem> getItemsIterator() {
        return itemList.values().iterator();
    }
}
